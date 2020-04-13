#include <stdio.h>
#include <stdlib.h>

int main()
{
    unsigned int size;
    scanf("%d", &size);
    int var[size];
    if (size == 0)
    {
        printf("empty array\n");
        return 0;
    }
    else
    {
        printf("%d\t", sizeof(var));
        return 0;
    }
}