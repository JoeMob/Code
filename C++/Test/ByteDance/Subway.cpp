#include <iostream>
 
using namespace std;
const int maxn = 100005;
int a[maxn];
int dpl[maxn],dpr[maxn];
 
int main()
{
    int T;
    scanf("%d",&T);
    while(T--)
    {
        int n;
        scanf("%d",&n);
        for(int i = 0; i < n; i++) scanf("%d",&a[i]);
        dpl[0] = 0;
        for(int i = 1; i < n; i++)
        {
            if(a[i] >= a[i - 1])
            {
                int j = dpl[i - 1];
                while(j - 1 >= 0 && a[i] >= a[j - 1])
                {
                    j = dpl[j - 1];
                }
                dpl[i] = j;
            }
            else
            {
                dpl[i] = i;
            }
        }
        dpr[n - 1] = n - 1;
        for(int i = n - 2; i >= 0; i--)
        {
            if(a[i] >= a[i + 1])
            {
                int j = dpr[i + 1];
                while(j + 1 < n && a[i] >= a[j + 1])
                {
                    j = dpr[j + 1];
                }
                dpr[i] = j;
            }
            else
            {
                dpr[i] = i;
            }
        }
        for(int i = 0; i < n; i++)
        {
            if(i == n - 1) printf("%d\n",dpr[i] - dpl[i]);
            else printf("%d ",dpr[i] - dpl[i]);
        }
    }
    return 0;
}