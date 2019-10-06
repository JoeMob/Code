# -*- coding: utf-8 -*-
from functools import reduce


def str2float(s):
    def char2num(s):
        digits = {'0': 0, '1': 1, '2': 2, '3': 3, '4': 4,
                  '5': 5, '6': 6, '7': 7, '8': 8, '9': 9}
        return digits[s]
    n = s.index('.')
    return reduce(lambda x, y: x*10+y, map(char2num, s[:n]))+reduce(lambda x, y: x/10+y/10, map(char2num, '0'+s[n+1:][::-1]))


print('str2float(\'123.456789\') =', str2float('123.456789'))
if abs(str2float('123.456789') - 123.456789) < 0.00001:
    print('测试成功!')
else:
    print('测试失败!')
