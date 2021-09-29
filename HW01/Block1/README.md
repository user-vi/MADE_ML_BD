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



