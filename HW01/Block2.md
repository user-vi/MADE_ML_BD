[![Gitter chat](https://badges.gitter.im/gitterHQ/gitter.png)](https://gitter.im/big-data-europe/Lobby)

# Changes

Version 2.0.0 introduces uses wait_for_it script for the cluster startup

# Hadoop Docker

## Supported Hadoop Versions
See repository branches for supported hadoop versions

## Quick Start

To deploy an example HDFS cluster, run:
```
  docker-compose up
```


Put file from local to docker-container of root:
```
  docker cp File_name namenode:/
```

Exec docker
```
  docker exec -it namenode /bin/bash
```

Put file from docker to hdfs:
```
  hdfs dfs -put File_name /
```

1 Create directory in root 
```
hdfs dfs -mkdir /bigdata
```
2 Create directory in created directory
```
hdfs dfs -mkdir /bigdata/hw01
```
3 How to remove filte without Trash
```
hdfs dfs -rm -skipTrash
```
4 Create empty file in the directory
```
hdfs dfs -touchz hdfs dfs -mkdir /bigdata/hw01/new_file.txt
```
5 Remove created file
```
hdfs dfs -rm -r /bigdata/hw01/new_file.txt
```
6 Remove created folders
```
hdfs dfs -rm -r /bigdata/hw01
```

1 Copy file in new  directory HDFS
```
hdfs fs -cp /bigdata/hw01/new_file.txt /bigdata/new_destination
```
2 Print file
```
hdfs fs -cat /bigdata/hw01/new_file.txt
```
3 Print some last rows
```
hdfs fs -tail /bigdata/hw01/new_file.txt
```
4 Print some first rows
```
hdfs fs -head /bigdata/hw01/new_file.txt
```
5 Move copy of file in hdfs on new location
```
hdfs fs -put /bigdata/hw01/new_file.txt /bigdata/new_destination
```

2 Change Replication factor
```
hdfs dfs -setrep -w 4 /bigdata/hw01/new_file.txt
```
It spent 20 mins
3 Filnd information about blocks and location
```
hdfs fsck /bigdata/hw01/new_file.txt -files -blocks -location
```
4 Get information of block by blockID
```
hdfs fsck -blockId blk_1079538107
```



