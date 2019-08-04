#include <iostream>
using namespace std;

int main()
{
    long long j = 1;
    for (int i = 1; i < 100000000; i++)
    {
        j = j + i;
    }
    cout << j;
    return 0;
}