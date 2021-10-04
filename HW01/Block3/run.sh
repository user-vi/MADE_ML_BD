set -x

HADOOP_STREAMING_JAR=/usr/local/hadoop/share/hadoop/tools/lib/hadoop-streaming.jar

hdfs dfs -rm -r /HW01/output

yarn jar $HADOOP_STREAMING_JAR \
	-files mapper.py,reducer.py \
	-mapper "./mean_variance_mapper.py" \
	-reducer "./mean_variance_reducer.py" \
	-numReduceTasks 1 \
	-input /HW01/input/ \
	-output /HW01/output
