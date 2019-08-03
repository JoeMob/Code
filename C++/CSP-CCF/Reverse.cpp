#include <iostream>
int main()
{
    int k = 0;
    int n = 0;
    std::cin >> n;
    int *p = (int *)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++)
    {
        std::cin >> p[i];
    }
    int *r = (int *)calloc(n, sizeof(int));
    for (int i = 0; i < n; i++)
    {
        r[i] = -p[i];
        for (int j = 0; j < i; j++)
        {
            if (p[i] == r[j])
            {
                k++;
            }
        }
    }
    std::cout << k;
    return 0;
}