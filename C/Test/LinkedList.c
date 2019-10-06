#include "stdio.h"
#include "stdlib.h"

typedef struct Node
{
    int data;
    struct Node *next;
} Node, *LinkedList;

LinkedList CreatLinkedList()
{
    int i, number;
    char c;
    LinkedList P = NULL;
    LinkedList L = (LinkedList)malloc(sizeof(Node));
    if (L == NULL)
    {
        printf("Memory not enough");
        return NULL;
    }
    L->next = NULL;
    P = L;
    while ((number = scanf_s("%d%c", &i, &c, sizeof(c))) != EOF)
    {
        P->next = (LinkedList)malloc(sizeof(Node));
        P = P->next;
        P->data = i;
        if (number >= 1 && c == '\n')
        {
            break;
        }
        else
        {
            ungetc(c, stdin);
        }
    }
    P->next = NULL;
    return L;
}

void DeleteByOrder(LinkedList L, int i)
{
    int a = 1;
    while (a < i)
    {
        L = L->next;
        a++;
    }
    LinkedList P = L->next;
    L->next = L->next->next;
    free(P);
}

void DeleteByData(LinkedList L, int i)
{
    LinkedList P;
    while (L->next != NULL)
    {
        P = L;
        L = L->next;
        if (L->data == i)
        {
            P->next = L->next;
            free(L);
            L = P->next;
        }
    }
}

int GetElem(LinkedList L, int i)
{
    int a = 0, e = 0;
    while (a < i)
    {
        L = L->next;
        a++;
    }
    e = L->data;
    return e;
}

void InsertElem(LinkedList L, int elem, int number)
{
    int a = 1;
    while (a < number)
    {
        L = L->next;
        a++;
    }
    LinkedList P = (LinkedList)malloc(sizeof(Node));
    P->next = L->next;
    P->data = elem;
    L->next = P;
}

void PrintLinkedList(LinkedList L)
{
    while (L->next != NULL)
    {
        L = L->next;
        printf("%d ", L->data);
    }
    printf("\n");
}

int main()
{
    int a = 0, b = 0;
    LinkedList L;
    L = CreatLinkedList();
    PrintLinkedList(L);
    scanf_s("%d %d", &a, &b);
    InsertElem(L, a, b);
    PrintLinkedList(L);
    scanf_s("%d", &a);
    DeleteByData(L, a);
    PrintLinkedList(L);
    scanf_s("%d", &a);
    DeleteByOrder(L, a);
    PrintLinkedList(L);
    scanf_s("%d", &a);
    printf("%d\n", GetElem(L, a));
    return 0;
}