#include <iostream>

int main()
{
    int number_n;
    int number[1000] = {0};
    int hashmap[10001] = {0};
    std::cin >> number_n;
    for (int i = 0; i < number_n; i++)
    {
        std::cin >> number[i];
        hashmap[number[i]]++;
    }
    int max = 0;
    for (int i = 1; i < 10001; i++)
    {
        if (hashmap[max] < hashmap[i])
        {
            max = i;
        }
    }
    std::cout << max << std::endl;
    return 0;
}