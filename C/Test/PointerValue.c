#include "stdio.h"

int main()
{
    int *a = (int *)malloc(sizeof(int));
    *a = 1;
    printf("%d", *a);
    return 0;
}