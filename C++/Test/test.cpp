#include <iostream>
using namespace std;

int main()
{
    string s1 = string("string1");
    string s2 = string("string2");
    const char *p1 = &s1[1];
    const char *p2 = &s2[1];
    cout << "Have cout." << p1 << p2 << s1 << s2 << endl;
    return 0;
}