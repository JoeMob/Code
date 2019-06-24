#include "stdio.h"
#include "string.h"
#include "stdlib.h"

typedef struct
{
    char vn[10];
    char vt[10][10][100];
} Grammar;

int main()
{
    char *input = (char *)calloc(sizeof(char), sizeof(char) * 100);
    printf("Plese input the grammar like:\nVt grammar1|grammar2|...\ninput # to finish input.\n");
    for (int i = 0; input[0] != '#'; i++)
    {
        gets(input);
        Grammar grammar;
        if (input[0] >= 'A' && input[0] <= 'Z' && input[1] == ' ')
        {
            grammar.vn[i] = input[0];
            int j = 0;
            int k = 0;
            for (int l = 2; input[l] != '\0'; l++, k++)
            {
                if (input[l] == '|')
                {
                    j++;
                    l++;
                    k = 0;
                }
                grammar.vt[i][j][k] = input[l];
            }
            printf("intput: %s\n", input);
            printf("vn: %c\n", grammar.vn[i]);
            for (int m = 0; m <= j; m++)
            {
                printf("vt: %s\n", grammar.vt[i][m]);
            }
        }
        else if (input[0] == '#')
        {
            printf("input completed.");
        }
        else
        {
            printf("error input");
        }
    };
    return 0;
}