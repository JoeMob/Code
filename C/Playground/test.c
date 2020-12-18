#include "stdio.h"
int main() {
  int i = 0x80000000;
  float k = 2;
  printf("%d,%u,%x", i, i, *(int *)&k);
  return 0;
}