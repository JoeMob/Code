#include "stdio.h"

int main()
{
    int offset[3] = {3, 2, 5};
    int n = 0;
    int resultSet[1500] = {1, 2, 3, 4, 6, 7, 0}, selection[3] = {0}, selectionMin = 0, resultOffset = 6, minSet[3] = {0};
    while (resultSet[1499] == 0)
    {
        selection[0] = 2 * resultSet[offset[0]];
        selection[1] = 3 * resultSet[offset[1]];
        selection[2] = 7 * resultSet[offset[2]];
        for (int i = 1; i < 3; i++)
        {
            if (selection[i] < selection[selectionMin])
            {
                selectionMin = i;
            }
        }
        resultSet[resultOffset] = selection[selectionMin];
        minSet[selectionMin] = 1;
        for (int i = 1; i < 3; i++)
        {
            if (selection[i] == selection[selectionMin])
            {
                minSet[i] = 1;
            }
        }
        for (int i = 0; i < 3; i++)
        {
            if (minSet[i] == 1)
            {
                offset[i]++;
                minSet[i] = 0;
            }
        }
        resultOffset++;
        selectionMin = 0;
    }
    while (1)
    {
        scanf("%d", &n);
        if (n == 0)
        {
            break;
        }
        printf("%d\n", resultSet[n - 1]);
    }
    return 0;
}