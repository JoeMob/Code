#include "stdio.h"

void swapInt(int *a, int *b) {
  *a = *a + *b;
  *b = *a - *b;
  *a = *a - *b;
}

void printIntArray(int array[], int length) {
  int i;
  for (i = 0; i < length - 1; i++) {
    printf("%d,", array[i]);
  }
  printf("%d\n", array[i]);
}

void EasySelectSortMove(int numbers[], int length) {
  int i, j, temp = 0;
  for (i = 0; i < length; i++) {
    j = 0;
    while (numbers[j] <= numbers[i] && j < i)
      j++;
    temp = numbers[i];
    for (int k = i; k > j; k--) {
      numbers[k] = numbers[k - 1];
    }
    numbers[j] = temp;
  }
}
// T(n) = n, O(n) = 4 ~ 1

void EasySelectSortSwap(int numbers[], int length) {
  for (int i = 1; i < length; i++) {
    for (int j = i; j > 0; j--) {
      if (numbers[j] < numbers[j - 1]) {
        swapInt(&numbers[j], &numbers[j - 1]);
      }
    }
  }
}
// T(n) = 3n ~ n, O(n) = 2 ~ 1

int main() {
  int numbers1[] = {1, 5, 2, 1, 3, 12, 233, 11, 6, 11111, 1998, 999999, -1, -2};
  int numbers2[] = {5, 2, 1, 3, 12, 233, 11, 6, 11111, 1998, 999999, -9999};
  int length1 = sizeof(numbers1) / sizeof(numbers1[0]);
  int length2 = sizeof(numbers2) / sizeof(numbers2[0]);
  printIntArray(numbers1, length1);
  EasySelectSortMove(numbers1, length1);
  printIntArray(numbers1, length1);
  printIntArray(numbers2, length2);
  EasySelectSortSwap(numbers2, length2);
  printIntArray(numbers2, length2);
  return 0;
}