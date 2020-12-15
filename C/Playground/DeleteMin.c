#include "stdio.h"

int deleteMin(int nums[], int length) {
  if (length == 0) {
    printf("There's no number.");
    return -1;
  } else {
    int i = 0, j = 1, lastNum = 0;
    for (; j < length; j++) {
      if (nums[i] > nums[j]) {
        i = j;
      }
    }
    int temp = nums[i];
    if (i != j) {
      nums[i] = nums[j - 1];
    } else {
      nums[i] = nums[j - 2];
    }
    return temp;
  }
}

int main() {
  int nums[] = {100, 2, 3, 4, 5, 6, 11, -10000, 213, 0, -1, 1, -2, -3, -4};
  int length = sizeof(nums) / sizeof(nums[0]);
  printf("The minimum is %d.\n", deleteMin(nums, length));
  for (int i = 0; i < length; i++) {
    printf("[%d] is %d,\n", i, nums[i]);
  }
  return 0;
}
