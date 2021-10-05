#!/usr/bin/python
import sys


def get_mean(chunk_size_prev, mean_prev, chunk_size_cur, mean_cur):
    return (chunk_size_prev * mean_prev + chunk_size_cur * mean_cur) / (chunk_size_prev + chunk_size_cur)


def get_var(chunk_size_prev, var_prev, mean_prev, chunk_size_cur, mean_cur, var_cur):
    first = (chunk_size_prev * var_prev + chunk_size_cur * var_cur) / (chunk_size_prev + chunk_size_cur)
    second = chunk_size_prev * chunk_size_cur * (((mean_prev - mean_cur) / (chunk_size_prev + chunk_size_cur)) ** 2)
    return first + second


#     if key == mean_cur:
#         chunk_size += value
#     else:
#         if mean_cur:
#             chunk_size_prev += chunk_size
#             var_prev = var
#             mean_prev = mean_cur

#             mean_cur = key
#             chunk_size = value
#             mean_cur = get_mean(chunk_size_prev, mean_prev, chunk_size, mean_cur)
#             var = get_var(chunk_size_prev, var_prev, mean_prev, chunk_size, key, 0)
#             continue

#         mean_cur = key
#         chunk_size = value
#         var = 0

# print(mean_cur, var)



chunk_size = 0
var = 0
mean_cur = 0
chunk_size_prev = 0

for line in sys.stdin:
    (key, value) = line.strip().split("\t")
    key = float(key)
    value = float(value)

    if key == mean_cur:
        mean_cur = key
        chunk_size += value
    else:
        if mean_cur != 0:
            chunk_size_prev += chunk_size
            var_prev = var
            mean_prev = mean_cur

            mean_cur = key
            chunk_size = value

            mean_cur = get_mean(chunk_size_prev, mean_prev, chunk_size, mean_cur)
            var = get_var(chunk_size_prev, var_prev, mean_prev, chunk_size, key, 0)
            mean_cur = None
            continue

        mean_cur = key
        chunk_size = value
if mean_cur is not None:
    chunk_size_prev += chunk_size
    var_prev = var
    mean_prev = mean_cur

    mean_cur = key
    chunk_size = value
    
    mean_cur = get_mean(chunk_size_prev, mean_prev, chunk_size, mean_cur)
    var = get_var(chunk_size_prev, var_prev, mean_prev, chunk_size, key, 0)

print(mean_cur, var)