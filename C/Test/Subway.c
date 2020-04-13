#include "stdio.h"

int check(int a, int subway[])
{
    
}

int main()
{
    int a, b;
    scanf("%d%d", &a, &b);
    int subway[100000][100000];
    for (int i = 0; i < a; i++)
    {
        for (int j = 0; j < b; j++)
            scanf("%d", &subway[j][i]);
    }
    check(a, subway);
}