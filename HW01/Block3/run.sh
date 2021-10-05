hdfs dfs -rm -r /output/
	
yarn jar /opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar \
	-files mapper.py,reducer.py \
	-mapper "python3 mapper.py" \
	-reducer "python3 reducer.py" \
	-numReduceTasks 1 \
	-input /Block3/AB_NYC_2019.csv \
	-output /output/
    
hdfs dfs -cat /output/part*