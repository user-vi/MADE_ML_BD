set -x

HADOOP_STREAMING_JAR=/opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar

hdfs dfs -rm -r /HW01/output

yarn jar $HADOOP_STREAMING_JAR \
	-files mean_variance_mapper.py, mean_variance_reducer.py \
	-mapper "python3 ./mean_variance_mapper.py" \
	-reducer "python3 ./mean_variance_reducer.py" \
	-numReduceTasks 1 \
	-input /AB_NYC_2019.csv \
	-output /output
