import pytest
import os
from pyspark.sql import SparkSession
from prepare_data_for_scoring_baseline import ParquetCreator, write_parquet

"""
Для запуска используй команду в терминале
```pytest -v ./test.py```

"""

def test_simple():
    assert 1

@pytest.fixture(scope='session')
def spark():
    return SparkSession.builder\
        .config("spark.local.ip", "192.168.0.5")\
        .master("local") \
        .appName("test") \
        .enableHiveSupport() \
        .getOrCreate()
#  Your hostname, MB483582.local resolves to a loopback address: 127.0.0.1; using 192.168.0.5 instead (on interface en0)

@pytest.mark.skip()
def test_tmpdir(spark, tmpdir):
    data = [
        (1, "one", "123"),
        (2, "two", None),
    ]
    df = spark.createDataFrame(data, ["id", "name", "phone"])
    p = tmpdir.mkdir("tmp")
    to_path = str(p) + '/df'
    df.repartition(1).write.mode("overwrite").parquet(to_path)
    assert 1

@pytest.fixture(scope='session')
def test_tmpdir_factory(spark, tmpdir_factory):
    """
    test data with null
    """
    data = [
        (1, "one", "123"),
        (2, "two", None),
    ]
    df = spark.createDataFrame(data, ["id", "name", "phone"])
    temp_dir = tmpdir_factory.mktemp('tmp')
    to_path = str(temp_dir) + '/df'
    df.repartition(1).write.mode("overwrite").parquet(to_path)
    return to_path

def test_dir(spark, test_tmpdir_factory):
    spark.read.parquet(test_tmpdir_factory)
    assert 1






