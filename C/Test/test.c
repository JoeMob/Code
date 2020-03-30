#include "stdio.h"
#include "stdlib.h"

int replaceNumber(int num, int set[])
{
    int k = 0;
    if (num < 0)
    {
        k = 1;
    }
    int t = 0;
    int s = 0;
    int i = 1;
    char string[1000] = {'\0'};
    // if (num < 0)
    // {
    //     string[0] = '-';
    // }
    while (abs(num) > 0)
    {
        string[t] = '0' + abs(num) % 10;
        t++;
        num = num / 10;
    }
    i = 0;
    char newString[1000] = {'\0'};
    while (string[i] != '\0')
    {
        if (string[i] == '1')
        {
            newString[i] = set[0] + '0';
        }
        if (string[i] == '2')
        {
            newString[i] = set[1] + '0';
        }
        if (string[i] == '3')
        {
            newString[i] = set[2] + '0';
        }
        if (string[i] == '4')
        {
            newString[i] = set[3] + '0';
        }
        if (string[i] == '5')
        {
            newString[i] = set[4] + '0';
        }
        if (string[i] == '6')
        {
            newString[i] = set[5] + '0';
        }
        if (string[i] == '7')
        {
            newString[i] = set[6] + '0';
        }
        if (string[i] == '8')
        {
            newString[i] = set[7] + '0';
        }
        if (string[i] == '9')
        {
            newString[i] = set[8] + '0';
        }
        i++;
    }
    i = 1;
    while (s < t)
    {
        num += (newString[s] - '0') * i;
        s++;
        i = i * 10;
    }
    if (k = 1)
    {
        num = -num;
    }
    // if (string[0] == '-')
    // {
    //     num = -num;
    // }
    return num;
}

int main()
{
    int num;
    int set[9];
    scanf("%d", &num);
    scanf("%d%d%d%d%d%d%d%d%d", &set[0], &set[1], &set[2], &set[3], &set[4], &set[5], &set[6], &set[7], &set[8]);
    printf("%d", replaceNumber(num, set));
    return 0;
}