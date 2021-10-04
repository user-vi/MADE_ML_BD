#!/usr/bin/python3
import sys


for line in sys.stdin:
    fields = line.rsplit(",", 7)
    if len(fields) > 7:
        price = fields[1]
        if price == 'price':
            continue
        if price is not None:
            print(str(price), 1, sep="\t", end="\n")
