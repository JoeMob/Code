#include "stdio.h"
#include "stdlib.h"

int tPow(int n)
{
    if (n == 0)
        return 1;
    else
        return 10 * tPow(n - 1);
}

int replaceNumber(int num, int set[])
{
    int res = 0;
    int dig = 0;
    int temp = 0;
    int minus = 0;
    if (num < 0)
    {
        num = -num;
        minus = 1;
    }
    while (num > 0)
    {
        temp = num % 10;
        res += tPow(dig) * set[temp - 1];
        num = num / 10;
        dig++;
    }
    if (minus == 1)
    {
        res = -res;
    }
    return res;
}

int main()
{
    int num;
    int set[9];
    scanf("%d", &num);
    for (int i = 0; i < 9; i++)
        scanf("%d", &set[i]);
    printf("%d", replaceNumber(num, set));
    return 0;
}