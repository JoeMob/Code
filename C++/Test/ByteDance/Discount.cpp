#include <iostream>
#include <algorithm>
#define maxn 1000000
using namespace std;

int a[maxn], b[maxn];

int main()
{
    int m, n;
    long long res = 0;
    cin >> m >> n;
    for (int i = 0; i < m; i++)
    {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++)
    {
        cin >> b[i];
    }
    sort(a, a + m);
    sort(b, b + n);
    int j = 0;
    for (int i = 0; i < n; i++)
    {
        if (m == 0)
        {
            res += b[i];
            continue;
        }
        if (a[0] > b[i])
        {
            res += b[i];
        }
        else
        {
            while (a[j + 1] <= b[i] && j + 1 < m)
            {
                j++;
            }
            res += b[i] - a[j];
        }
    }
    cout << res << endl;
    return 0;
}