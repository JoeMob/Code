#include <iostream>
#include <stdlib.h>

class Node
{
public:
    int data;
    Node *prior, *next;
} * LinkList;

//初始化
void InitializeLinkedList(Node **head)
{
    if ((*head = (Node *)malloc(sizeof(Node))) == NULL)
    {
        exit(0);
    }
    /*    双向链表，前驱指自己，后继指自己 */
    (*head)->prior = *head;
    (*head)->next = *head;
}

//求表长
int ListLength(Node *head)
{
    Node *p = head;
    int size = 0;
    while (p->next != head)
    {
        p = p->next;
        size++;
    }
    return (size);
}

//插入
void ListInsert(Node *head, int i, int x)
{

    Node *p, *s;
    int j = 0;
    p = head->next;
    while (p != head && j < i)
    {
        p = p->next;
        j++;
    }
    if (j != i)
    {
        printf("\n插入位置不合法！");
        return;
    }
    if ((s = (Node *)malloc(sizeof(Node))) == NULL)
    {
        exit(0);
    }
    s->data = x;
    s->prior = p->prior;
    p->prior->next = s;
    s->next = p;
    p->prior = s;
}

//输入
void InputNumber(Node *head)
{
    int input, i = 0;
    char c;
    std::cin >> input >> c;
    while (true)
    {
        if (input >= 0 && i == 0)
        {
            head->data = 1;
            ListInsert(head, i, input);
        }
        else
        {
            if (head->next->data >= 0)
            {
                ListInsert(head, i, input);
            }
            else
            {
                ListInsert(head, i, input);
            }
        }
        i++;
        if (c == '#')
        {
            break;
        }
        std::cin >> input >> c;
    }
}

//输出
void OutputNumber(Node *head, int sign)
{
    Node *r = head->next;
    while (r->data == 0 && r != head->prior)
    {
        r = r->next;
    }
    printf("结果是:");
    printf("%d", r->data);
    r = r->next;
    while (r != head)
    {
        if (r->data < 10)
        {
            printf("000");
            printf("%d", r->data);
        }
        else if (r->data < 100)
        {
            printf("00");
            printf("%d", r->data);
        }
        else if (r->data < 1000)
        {
            printf("0");
            printf("%d", r->data);
        }
        else
        {
            printf("%d", r->data);
        }
        r = r->next;
    }
    printf("\n");
}

//加法
void add(Node *head1, Node *head2, Node *head3)
{
    int z = 0;
    int e;
    Node *p1, *p2;
    p1 = head1->prior;
    p2 = head2->prior;
    while (p1 != head1 && p2 != head2)
    {
        e = p1->data + p2->data + z;
        if (e >= 10000)
        {
            z = (e - (e % 10000)) / 10000;
            e = e % 10000;
        }
        else
        {
            z = 0;
        }
        ListInsert(head3, 0, e);
        p1 = p1->prior;
        p2 = p2->prior;
    }
    if (p1 == head1 && p2 != head2)
    {
        while (p2 != head2)
        {
            e = p2->data + z;
            if (e >= 10000)
            {
                z = (e - (e % 10000)) / 10000;
                e = e % 10000;
            }
            else
            {
                z = 0;
            }
            ListInsert(head3, 0, e);
            p2 = p2->prior;
        }
        if (z != 0)
        {
            ListInsert(head3, 0, z);
        }
    }
    else if (p1 != head1 && p2 == head2)
    {
        while (p1 != head1)
        {
            e = p1->data + z;
            if (e >= 10000)
            {
                z = (e - (e % 10000)) / 10000;
                e = e % 10000;
            }
            else
            {
                z = 0;
            }
            ListInsert(head3, 0, e);
            p1 = p1->prior;
        }
        if (z != 0)
            ListInsert(head3, 0, z);
    }
    else
    {
        if (z != 0)
            ListInsert(head3, 0, z);
    }
}

//交换
int change(Node *head1, Node *head2)
{
    int length1, length2, r = 2;
    length1 = ListLength(head1);
    length2 = ListLength(head2);
    Node *p1, *p2;
    p1 = head1->next;
    p2 = head2->next;
    if (length1 > length2)
    {
        r = 0;
        return (r);
    }
    else if (length1 < length2)
    {
        r = 1;
        return (r);
    }
    else
    {
        int i = 0;
        for (i = 0; i < length1; i++)
        {
            if (p1->data > p2->data)
            {
                r = 0;
                return (r);
            }
            else if (p2->data > p1->data)
            {
                r = 1;
                return (r);
            }
            else
            {
                p1 = p1->next;
                p2 = p2->next;
                r = 2;
            }
        }
    }
    return (r);
}

//减法
void minus(Node *head1, Node *head2, Node *head3)
{
    int z = 0, x = -1;
    int e;
    Node *p1, *p2;
    p1 = head1->prior;
    p2 = head2->prior;
    x = change(head1, head2);
    if (x == 0)
    {
        while (p1 != head1 && p2 != head2)
        {
            p1->data = p1->data + z;
            if (p1->data >= p2->data)
            {
                e = p1->data - p2->data;
                ListInsert(head3, 0, e);
                p1 = p1->prior;
                p2 = p2->prior;
                z = 0;
            }
            else
            {
                e = 10000 + p1->data - p2->data;
                ListInsert(head3, 0, e);
                p1 = p1->prior;
                p2 = p2->prior;
                z = -1;
            }
        }
        p1->data = p1->data + z;
        while (p1 != head1)
        {
            e = p1->data;
            ListInsert(head3, 0, e);
            p1 = p1->prior;
        }
    }
    else if (x == 1)
    {
        p2 = head1->prior;
        p1 = head2->prior;
        while (p1 != head2 && p2 != head1)
        {
            p1->data = p1->data + z;
            if (p1->data >= p2->data)
            {
                e = p1->data - p2->data;
                ListInsert(head3, 0, e);
                p1 = p1->prior;
                p2 = p2->prior;
                z = 0;
            }
            else
            {
                e = 10000 + p1->data - p2->data;
                ListInsert(head3, 0, e);
                p1 = p1->prior;
                p2 = p2->prior;
                z = -1;
            }
        }
        p1->data = p1->data + z;
        while (p1 != head2)
        {
            e = p1->data;
            ListInsert(head3, 0, e);
            p1 = p1->prior;
        }
        head3->next->data = -1 * head3->next->data;
    }
    else
    {
        head3->next->data = 0;
    }
}

//加减判断
void count(Node *head1, Node *head2, Node *head3, char ch)
{
    Node *p1, *p2;
    p1 = head1->next;
    p2 = head2->next;
    if (head1->data == 1 && head2->data == 1)
    {
        if (ch == '+')
        {
            add(head1, head2, head3);
        }
        else
        {
            minus(head1, head2, head3);
        }
    }
    else if (head1->data == 1 && head2->data == 0)
    {
        if (ch == '+')
        {
            head2->next->data *= -1;
            minus(head1, head2, head3);
        }
        else
        {
            head2->next->data *= -1;
            add(head1, head2, head3);
        }
    }
    else if (head1->data == 0 && head2->data == 1)
    {
        if (ch == '+')
        {
            head1->next->data *= -1;
            minus(head2, head1, head3);
        }
        else
        {
            head1->next->data *= -1;
            head2->next->data *= -1;
            add(head1, head2, head3);
            head3->next->data *= -1;
        }
    }
    else
    {
        if (ch == '+')
        {
            head1->next->data *= -1;
            head2->next->data *= -1;
            add(head1, head2, head3);
            head3->next->data *= -1;
        }
        else
        {
            head1->next->data *= -1;
            head2->next->data *= -1;
            minus(head2, head1, head3);
        }
    }
}

int main(int argc, const char *argv[])
{
    char ch, ch1;
    while (true)
    {
        Node *a, *b, *c;
        InitializeLinkedList(&a);
        InitializeLinkedList(&b);
        InitializeLinkedList(&c);
        printf("请输入数A(#为结尾):");
        InputNumber(a);
        printf("请输入数B(#为结尾):");
        InputNumber(b);
        printf("请选择操作符：+和-:");
        std::cin >> ch1;
        if (ch1 == '+' || ch1 == '-')
        {
            count(a, b, c, ch1);
            OutputNumber(c, 1);
        }
        else
        {
            printf("不支持%c运算", ch1);
        }
    }
    return (0);
}