#include "stdio.h"

int main()
{
    char a[5][3] = {'1', '2', 0, '3', '4', 0, '5', '6', '\0', '7', '8', '\0', '9', '0', '\0'};
    printf("%s\n", a[0]);
    return 0;
}