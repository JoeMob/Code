#include "stdio.h"

int check(int a[], int b[], int n)
{
    int k = 0;
    int dig = 0;
    for (int i = 0; i <= n; i++)
    {
        if (a[i] != b[i])
        {
            k = a[i] - b[i];
            dig = i;
            break;
        }
        if (i == n)
        {
            return 1;
        }
    }
    if (dig == n - 1)
    {
        return 1;
    }
    for (int j = dig; j < n; j++)
    {
        if (a[j] != b[j] + k)
        {
            dig = j;
            break;
        }
    }
    for (int k = dig; k < n; k++)
    {
        if (a[k] != b[k])
        {
            return 0;
        }
    }
    return 1;
}

int main()
{
    int t = 0;
    int num[2][1000000];
    int result[1000000];
    scanf("%d", &t);
    for (int i = 0; i < t; i++)
    {
        int n = 0;
        scanf("%d", &n);
        for (int j = 0; j < n; j++)
            scanf("%d", &num[0][j]);
        for (int j = 0; j < n; j++)
            scanf("%d", &num[1][j]);
        result[i] = check(num[0], num[1], n);
        for (int j = 0; j < n; j++)
        {
            num[0][j] = 0;
            num[1][j] = 0;
        }
    }
    for (int i = 0; i < t; i++)
    {
        if (result[i] == 1)
        {
            printf("YES\n");
        }
        if (result[i] == 0)
        {
            printf("NO\n");
        }
    }
    return 0;
}
