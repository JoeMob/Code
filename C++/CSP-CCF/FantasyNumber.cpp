#include <iostream>
using namespace std;
int main()
{
    long long mod = 1000000007;
    int n = 0;
    cin >> n;
    long long status[1000][6] = {0};
    for (int i = 1; i <= n; i++)
    {
        status[i][0] = 1;
        status[i][1] = (status[i - 1][0] + status[i - 1][1] * 2) % mod;
        status[i][2] = (status[i - 1][0] + status[i - 1][2]) % mod;
        status[i][3] = (status[i - 1][1] + status[i - 1][3] * 2) % mod;
        status[i][4] = (status[i - 1][1] + status[i - 1][2] + status[i - 1][4] * 2) % mod;
        status[i][5] = (status[i - 1][3] + status[i - 1][4] + status[i - 1][5] * 2) % mod;
    }
    cout << status[n][5];
    return 0;
}