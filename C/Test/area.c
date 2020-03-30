#include "stdio.h"
#define PI 3.1415926

int main()
{
    int i = 0;
    double area = 0;
    int num = 0;
    int r[1000];
    scanf("%d", &num);
    for (i = 0; i < num; i++)
    {
        scanf("%d", &r[i]);
    }
    i--;
    while (i >= 0)
    {
        area += r[i] * r[i] * PI;
        i--;
        if (i >= 0)
        {
            area -= r[i] * r[i] * PI;
        }
        i--;
    }
    printf("%.5f", area);
    return 0;
}