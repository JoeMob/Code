#include "stdio.h"
#include "stdlib.h"
#include "string.h"
#include "Stack.c"

typedef struct
{
    char vn[10];
    char vt[10][10][100];
} Grammar;

typedef struct
{
    char vn[10];
    char first[10][10];
} FirstSet;

typedef struct
{
    char vn[10];
    char follow[10][10];
} FollowSet;

typedef struct
{
    char vn[10];
    char select[10][10];
} SelectSet;

int Search(Grammar grammar, char vn)
{
    int i = 0;
    for (i = 0; grammar.vn[i] != vn; i++)
    {
    }
    return i;
}

void Single(char (*array)[10][10])
{
    char new_array[10][10] = {0};
    int i = 0;
    char flag[1000] = {0};
    for (i = 0; i < 10; i++)
    {
        int length = 0;
        for (int j = 0; (*array)[i][j] != 0; j++)
        {
            if (flag[(*array)[i][j]] == 0)
            {
                new_array[i][length] = (*array)[i][j];
                length++;
                flag[(*array)[i][j]] = 1;
            }
        }
        int j = 0;
        while (j < 1000)
        {
            flag[j] = 0;
            j++;
        }
    }
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; j < 10; j++)
        {
            (*array)[i][j] = new_array[i][j];
        }
    }
}

void DigingFirst(CharStack *firststack[10], Grammar grammar, int i, int j, int root)
{
    int l = Search(grammar, grammar.vt[i][j][0]);
    for (j = 0; grammar.vt[l][j][0] != 0; j++)
    {
        if (grammar.vt[l][j][0] >= 'a' && grammar.vt[l][j][0] <= 'z')
        {
            PushChar(firststack[root], grammar.vt[l][j][0]);
        }
        if (grammar.vt[l][j][0] >= 'A' && grammar.vt[l][j][0] <= 'Z')
        {
            DigingFirst(firststack, grammar, l, j, root);
        }
        if (grammar.vt[l][j][0] == '@')
        {
            PushChar(firststack[root], grammar.vt[l][j][0]);
        }
    }
}

void DigFirst(CharStack *firststack[10], Grammar grammar)
{
    for (int i = 0; grammar.vt[i][0][0] != 0; i++)
    {
        for (int j = 0; grammar.vt[i][j][0] != 0; j++)
        {
            if (grammar.vt[i][j][0] >= 'a' && grammar.vt[i][j][0] <= 'z')
            {
                PushChar(firststack[i], grammar.vt[i][j][0]);
                printf("PushChar %c in to firststack[%d].\n", grammar.vt[i][j][0], i);
            }
            if (grammar.vt[i][j][0] >= 'A' && grammar.vt[i][j][0] <= 'Z')
            {
                DigingFirst(firststack, grammar, i, j, i);
            }
            if (grammar.vt[i][j][0] == '@')
            {
                PushChar(firststack[i], grammar.vt[i][j][0]);
                printf("PushChar %c in to firststack[%d].\n", grammar.vt[i][j][0], i);
            }
        }
    }
}

FirstSet ConvertGrammarToFirstSet(Grammar grammar)
{
    FirstSet firstset = {.vn = 0, .first = 0};
    CharStack *firststack[10];
    for (int i = 0; i < 10; i++)
    {
        firststack[i] = (CharStack *)calloc(1, sizeof(CharStack *));
        InitCharStack(firststack[i]);
    }
    for (int i = 0; i < 10; i++)
    {
        firstset.vn[i] = grammar.vn[i];
    }
    DigFirst(firststack, grammar);
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; firststack[i]->size > 0; j++)
        {
            firstset.first[i][j] = PopChar(firststack[i]);
        }
    }
    Single(&firstset.first);
    return firstset;
}

void DigFollow(CharStack *followstack[10], Grammar grammar, FirstSet firstset)
{
    for (int i = 0; grammar.vt[i][0][0] != 0; i++)
    {
        for (int j = 0; grammar.vt[i][j][0] != 0; j++)
        {
            for (int k = 0; grammar.vt[i][j][k] != 0; k++)
            {
                if (grammar.vt[i][j][k] >= 'A' && grammar.vt[i][j][k] <= 'Z')
                {
                    if (grammar.vt[i][j][k + 1] != 0)
                    {
                        if (grammar.vt[i][j][k + 1] >= 'a' && grammar.vt[i][j][k + 1] <= 'z')
                        {
                            int vn = Search(grammar, grammar.vt[i][j][k]);
                            PushChar(followstack[vn], grammar.vt[i][j][k + 1]);
                            printf("PushChar %c in to followstack[%d].\n", grammar.vt[i][j][k + 1], vn);
                        }
                        if (grammar.vt[i][j][k + 1] == '@')
                        {
                            int vn = Search(grammar, grammar.vt[i][j][k]);
                            PushChar(followstack[vn], '#');
                        }
                        if (grammar.vt[i][j][k + 1] >= 'A' && grammar.vt[i][j][k + 1] <= 'Z')
                        {
                            int vn = Search(grammar, grammar.vt[i][j][k]);
                            int vnR = Search(grammar, grammar.vt[i][j][k + 1]);
                            for (int l = 0; firstset.first[vn][l] != 0; l++)
                            {
                                PushChar(followstack[vn], firstset.first[vnR][l]);
                            }
                        }
                    }
                }
            }
        }
    }
}

FollowSet ConvertGrammarFirstSetToFollowSet(Grammar grammar, FirstSet firstset)
{
    FollowSet followset = {.vn = 0, .follow = 0};
    CharStack *followstack[10];
    for (int i = 0; i < 10; i++)
    {
        followstack[i] = (CharStack *)calloc(1, sizeof(CharStack *));
        InitCharStack(followstack[i]);
    }
    for (int i = 0; i < 10; i++)
    {
        followset.vn[i] = grammar.vn[i];
    }
    DigFollow(followstack, grammar, firstset);
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; followstack[i]->size > 0; j++)
        {
            followset.follow[i][j] = PopChar(followstack[i]);
            printf("Popedchar %c\n", followset.follow[i][j]);
        }
    }
    Single(&followset.follow);
    return followset;
}

void DigSelect(CharStack *selectstack[10], Grammar grammar, FirstSet firstset, FollowSet followset) {}

SelectSet ConvertGrammarFirstSetFollowSetToSelectSet(Grammar grammar, FirstSet firstset, FollowSet followset)
{
    SelectSet selectset;
    CharStack *selectstack[10];
    for (int i = 0; i < 10; i++)
    {
        selectstack[i] = (CharStack *)calloc(1, sizeof(CharStack *));
        InitCharStack(selectstack[i]);
    }
    for (int i = 0; i < 10; i++)
    {
        selectset.vn[i] = grammar.vn[i];
    }
    DigSelect(selectstack, grammar, firstset, followset);
    for (int i = 0; i < 10; i++)
    {
        for (int j = 0; selectstack[i]->size > 0; j++)
        {
            selectset.select[i][j] = PopChar(selectstack[i]);
        }
    }
    return selectset;
}

int VerifyLL1(SelectSet selectset)
{
    return 0;
}

int main()
{
    char *input = (char *)calloc(1000, sizeof(char));
    printf("Plese input the grammar like:\nVt grammar1|grammar2|...\ninput # to finish input.\n");
    Grammar grammar = {.vt = 0, .vn = 0};
    for (int i = 0; input[0] != '#'; i++)
    {
        gets(input);
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
            printf("input completed.\n");
        }
        else
        {
            printf("error input\n");
        }
    };
    FirstSet firstset = {.vn = 0, .first = 0};
    firstset = ConvertGrammarToFirstSet(grammar);
    FollowSet followset = {.vn = 0, .follow = 0};
    followset = ConvertGrammarFirstSetToFollowSet(grammar, firstset);
    ConvertGrammarFirstSetFollowSetToSelectSet(grammar, firstset, followset);
    printf("Print firstset:\n");
    for (int i = 0; firstset.first[i][0] != 0; i++)
    {
        printf("%c: ", firstset.vn[i]);
        for (int j = 0; j < 10; j++)
        {
            printf("%c ", firstset.first[i][j]);
        }
        printf("\n");
    }
    printf("Print followtset:\n");
    for (int i = 0; i < 10; i++)
    {
        if (followset.follow[i][0] != 0)
        {
            printf("%c: ", followset.vn[i]);
            for (int j = 0; j < 10; j++)
            {
                printf("%c ", followset.follow[i][j]);
            }
            printf("\n");
        }
    }
    printf("Not LL1.");
    return 0;
}