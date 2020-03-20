#include "stdio.h"
#include "stdlib.h"

int *twoSum(int *nums, int numsSize, int target, int *returnSize)
{
    *returnSize = 2;
    int **map = (int **)calloc(2, sizeof(int *));
    int max = 0, min = 0;
    int *result = (int *)calloc(2, sizeof(int));
    for (int i = 0; i < numsSize; i++)
    {
        if (nums[i] < min)
        {
            min = nums[i];
        }
        if (nums[i] > max)
        {
            max = nums[i];
        }
    }
    if ((target - max) < min)
    {
        min = target - max;
    }
    if ((target - min) > max)
    {
        max = target - min;
    }
    if (abs(min) > abs(max))
    {
        max = abs(min);
    }
    if (min < 0)
    {
        map[0] = (int *)calloc((max + max + 1), sizeof(int));
        map[0] = map[0] + max;
        map[1] = (int *)calloc((max + max + 1), sizeof(int));
        map[1] = map[1] + max;
    }
    if (min >= 0)
    {
        map[0] = (int *)calloc((max + 1), sizeof(int));
        map[1] = (int *)calloc((max + 1), sizeof(int));
    }
    for (int i = 0; i < numsSize; i++)
    {
        map[0][nums[i]] = i;
        map[1][nums[i]] = 1;
        if (map[1][target - nums[i]] == 1 && map[0][target - nums[i]] != i)
        {
            result[0] = map[0][target - nums[i]];
            result[1] = i;
            free(map);
            return result;
        }
    }
    free(map);
    return result;
}

int main()
{
    int num[] = {3, 2, 4, 2};
    int returnsize;
    int *result = twoSum(num, 4, 4, &returnsize);
    printf("%d,%d", result[0], result[1]);
    return 0;
}
