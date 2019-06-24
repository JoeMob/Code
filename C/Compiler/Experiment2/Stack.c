#include "stdio.h"
#include "stdlib.h"
#include "malloc.h"

typedef struct
{
    char *top;
    int size;
} CharStack;

int InitCharStack(CharStack *T)
{
    T->size = 0;
    T->top = (char *)calloc(sizeof(char), 10000);
    return 0;
}

void PushChar(CharStack *T, char c)
{
    T->size++;
    T->top[T->size] = c;
}

char PopChar(CharStack *T)
{
    if (T->size == 0)
    {
        return -1;
    }
    else
    {
        char c = T->top[T->size];
        T->size--;
        return c;
    }
}

char CharTop(CharStack *T)
{
    return (T->top[T->size]);
}

typedef struct
{
    float *top;
    int size;
} FloatStack;

int InitFloatStack(FloatStack *T)
{
    T->size = 0;
    T->top = (float *)calloc(sizeof(float), 10000);
    return 0;
}

void PushFloat(FloatStack *T, float i)
{
    T->size++;
    T->top[T->size] = i;
}

float PopFloat(FloatStack *T)
{
    if (T->size == 0)
    {
        return -1;
    }
    else
    {
        float i = T->top[T->size];
        T->size--;
        return i;
    }
}

int FlotTop(FloatStack *T)
{
    return (T->top[T->size]);
}