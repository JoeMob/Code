#include "stdio.h"

int check(int n, int a[])
{
    int res = 0, divideR = 0, remainR = 0;
    for (int i = n - 1; i > 0; i--)
    {
        if (a[i - 1] > a[i])
        {
            remainR = a[i - 1] % a[i];
            divideR = a[i - 1] / a[i];
            if (remainR == 0)
            {
                res += divideR - 1;
                a[i - 1] = a[i];
            }
            if (remainR > 0)
            {
                res += divideR;
                a[i - 1] /= (divideR + 1);
            }
        }
    }
    return res;
}

int main()
{
    int n = 0;
    int a[10000];
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
        scanf("%d", &a[i]);
    printf("%d", check(n, a));
}