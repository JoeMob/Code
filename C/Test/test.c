#include "stdio.h"
#include "stdlib.h"
#include "string.h"

int main()
{
    int dotP, lastslashP, endP;
    char str[1000];
    scanf("%s", str);
    for (int i = 0; str[i] == '\0'; i++)
    {
        if (str[i] == '/')
        {
            lastslashP = i;
        }
        if (str[i] == '.')
        {
            dotP = i;
        }
        if (!str[i + 1])
        {
            endP = i;
        }
    }
    char name[100];
    char path[100];
    char type[100];
    strncpy(name, str + lastslashP, dotP - lastslashP);
    strncpy(path, str, lastslashP);
    strncpy(type, str + dotP, lastslashP);
    printf("Name:%s\n,Path:%s\n,Type:%s\n", name, path, type);
    return 0;
}