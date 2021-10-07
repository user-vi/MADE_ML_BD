# MADE_ML_BD


Check scripts

```
chmod u+x ./mapper.py
chmod u+x ./reducer.py

cat AB_NYC_2019.csv | head -n 5 | python3 ./mapper.py | sort | python3 ./reducer.py

```

Run map-reduce

```
./run.sh
```
