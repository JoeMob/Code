#include "stdio.h"
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool isPalindrome(int x)
{
	int reverse = 0;
	int a;
	if (x != 0 && x % 10 == 0)
	{
		return false;
	}
	else
	{
		while (x > reverse)
		{
			reverse = reverse * 10 + x % 10;
			if (x == reverse)
			{
				return true;
			}
			x /= 10;
		}
		if (reverse == x)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}

int main()
{
	int x;
	printf("Input X:");
	scanf("%d", &x);
	if (isPalindrome(x))
		printf("Is palindrome\n");
	else
		printf("Not palindrome\n");
return 0;
}