# -*- coding: utf-8 -*-


def move(n, stick1, stick2, stick3):
    if (n == 1):
        print(stick1, '->', stick3)
    else:
        move(n-1, stick1, stick3, stick2)
        move(1, stick1, stick2, stick3)
        move(n-1, stick2, stick1, stick3)


n = int(input())
move(n, 'A', 'B', 'C')
