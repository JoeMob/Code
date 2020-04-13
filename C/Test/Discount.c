#include "stdio.h"
#include "stdlib.h"
#define maxn 1000000

int cmp(const void *a, const void *b)
{
    return *(int *)a - *(int *)b; //由小到大排序
}

int main()
{
    int res = 0, m, n;
    int a[maxn], b[maxn];
    scanf("%d%d", &m, &n);
    for (int i = 0; i < m; i++)
    {
        scanf("%d", &a[i]);
    }
    for (int i = 0; i < n; i++)
    {
        scanf("%d", &b[i]);
    }
    qsort(a, m, sizeof(int), cmp);
    qsort(b, n, sizeof(int), cmp);
    int j = 0;
    for (int i = 0; i < n; i++)
    {
        if (m == 0)
        {
            res += b[i];
            continue;
        }
        if (a[0] > b[i])
        {
            res += b[i];
        }
        else
        {
            while (a[j + 1] <= b[i] && j + 1 < m)
            {
                j++;
            }
            res += b[i] - a[j];
        }
    }
    printf("%d", res);
    return 0;
}