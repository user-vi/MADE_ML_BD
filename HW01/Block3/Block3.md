# MADE_ML_BD


Check scripts

```
chmod u+x ./mean_mapper.py
chmod u+x ./mean_reducer.py

cat AB_NYC_2019.csv | head -n 5 | python3 ./mean_mapper.py | sort | python3 ./mean_reducer.py


chmod u+x variance_mapper.py 
chmod u+x variance_reducer.py 

cat AB_NYC_2019.csv | head -n 5 | python3 ./variance_mapper.py | sort | python3 ./variance_reducer.py

```