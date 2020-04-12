#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int main()
{
    vector<int> v = {10, 9, 8, 6, 7, 2, 5, 1};
    cout << "Elements before sorting" << endl;
    for (const auto &i : v)
        cout << i << ' ' << endl;
    cout << "Elements after sorting" << endl;
    sort(v.begin(), v.end());
    for (const auto &i : v)
        cout << i << ' ' << endl;
    return 0;
}