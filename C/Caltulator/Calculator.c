#include "stdio.h"
#include "stdlib.h"
#include "Stack.c"

void Print(FloatStack *F)
{
    printf("%lf\n", F->top[F->size]);
}
float powf(float n, float i)
{
    if (i == 0)
    {
        return 1;
    }
    else if (i == 1)
    {
        return n;
    }
    else if (i > 1)
    {
        return n * powf(n, i - 1);
    }
    else if (i < -1)
    {
        return powf(n, i + 1) / n;
    }
    else if (i == -1)
    {
        return 1 / n;
    }
}
void Calculate(CharStack *C, FloatStack *F)
{
    char d = PopChar(C);
    float b = PopFloat(F);
    float a = PopFloat(F);
    char c = PopChar(C);
    printf("%lf %c %lf\n", a, c, b);
    switch (c)
    {
    case '+':
        PushFloat(F, a + b);
        //printf("RUA!\n");
        Print(F);
        break;
    case '-':
        PushFloat(F, a - b);
        //printf("RUA!!\n");
        Print(F);
        break;
    case '*':
        PushFloat(F, a * b);
        //printf("RUA!!!\n");
        Print(F);
        break;
    case '/':
        PushFloat(F, a / b);
        //printf("RUA!!!!\n");
        Print(F);
        break;
    case '^':
        PushFloat(F, powf(a, b));
        //printf("RUA!!!!!\n");
        Print(F);
    }
    if (C->top[C->size] == ')' && C->top[C->size - 1] == '(')
    {
        PopChar(C);
        PopChar(C);
    }
    PushChar(C, d);
    if (C->top[C->size] == ')' && C->top[C->size - 1] == '(')
    {
        PopChar(C);
        PopChar(C);
    }
    if (C->top[C->size] == ')' && C->top[C->size - 1] != '(')
    {
        //printf("RUA??\n");
        Calculate(C, F);
    }
}
void Scan(CharStack *C, FloatStack *F)
{
    char Priority[8] = {'#', '+', '-', '*', '/', '(', ')', '^'};
    int PriorityLN[8] = {0, 1, 1, 2, 2, 0, 4, 3};
    int PriorityRN[8] = {0, 1, 1, 2, 2, 4, 0, 3};
    int PriorityL = -1;
    int PriorityR = 0;
    char c = 0;
    while (C->top[C->size] != '#' || F->size != 1 || C->size != 1)
    {
        c = getchar();
        //printf("Get daze!\n");
        if (c != '\n')
        {
            if (c == '.' || (c >= '0' && c <= '9'))
            {
                char c1[100] = {0};
                int i = 0;
                c1[i] = c;
                while (c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')' && c != '^' && c != '#')
                {
                    i++;
                    c = getchar();
                    //printf("Get daze!!\n");
                    if (c != '+' && c != '-' && c != '*' && c != '/' && c != '(' && c != ')' && c != '^' && c != '#')
                        c1[i] = c;
                    else
                        PushChar(C, c);
                }
                PushFloat(F, atof(c1));
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '^' || c == '#')
            {
                PushChar(C, c);
            }
        }
        if (C->top[C->size] == '+' || C->top[C->size] == '-' || C->top[C->size] == '*' || C->top[C->size] == '/' || C->top[C->size] == '(' || C->top[C->size] == ')' || C->top[C->size] == '^' || C->top[C->size] == '#')
        {
            if (C->size <= 1)
            {
                //printf("ZaiMa?BuZai,CNM.1\n");
            }
            else
            {
                char first;
                char second;
                int j = 0;
                first = C->top[C->size];
                second = C->top[C->size - 1];
                if (C->size <= 1)
                {
                    //printf("ZaiMa?BuZai,CNM.1\n");
                }
                while (first != Priority[j])
                {
                    j++;
                }
                PriorityR = PriorityRN[j];
                j = 0;
                while (second != Priority[j])
                {
                    j++;
                }
                PriorityL = PriorityLN[j];
                j = 0;
                if (PriorityL < PriorityR)
                {
                    //printf("ZaiMa?BuZai,CNM.2\n");
                }
                else
                {
                    //printf("RUA?\n");
                    Calculate(C, F);
                }
            }
        }
    }
}
int main()
{
    CharStack *C = (CharStack *)calloc(sizeof(*C), 1);
    FloatStack *F = (FloatStack *)calloc(sizeof(*F), 1);
    InitCharStack(C);
    InitFloatStack(F);
    Scan(C, F);
    printf("Result:");
    Print(F);
    /*printf("Number:\n");
    for (int i = C->size; i > 0; i--)
        printf("%lf\n", PopFloat(F));
    printf("Symbol:\n");
    for (int i = C->size; i > 0; i--)
        printf("%c\n", PopChar(C));*/
    return 0;
}