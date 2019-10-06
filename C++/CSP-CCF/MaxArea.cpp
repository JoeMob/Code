#include <iostream>
#include <vector>

using namespace std;

int MaxArea(vector<int> x, int n)
{
    int width = 1;
    int max = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 1; i + j < n && x[i + j] >= x[i]; j++)
        {
            width++;
        }
        for (int j = 1; i - j > -1 && x[i - j] >= x[i]; j++)
        {
            width++;
        }
        if (max < x[i] * width)
        {
            max = x[i] * width;
        }
        width = 1;
    }
    return max;
}

int main()
{
    vector<int> x;
    int n = 0;
    cin >> n;
    for (int i = 0; i < n; i++)
    {
        int val;
        cin >> val;
        x.push_back(val);
    }
    cout << MaxArea(x, n) << endl;
    return 0;
}