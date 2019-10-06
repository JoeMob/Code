#include <iostream>
#include <cstring>

struct window
{
    int x1, y1, x2, y2, num;
};

struct click
{
    int x, y;
};

int main()
{
    window windows[10];
    click clicks[10];
    int windows_n, clicks_n;
    std::cin >> windows_n >> clicks_n;
    for (int i = 0; i < windows_n; i++)
    {
        std::cin >> windows[i].x1 >> windows[i].y1 >> windows[i].x2 >> windows[i].y2;
        windows[i].num = i + 1;
    }
    for (int i = 0; i < clicks_n; i++)
    {
        std::cin >> clicks[i].x >> clicks[i].y;
    }
    for (int i = 0; i < clicks_n; i++)
    {
        bool flag = true;
        for (int j = windows_n - 1; j > -1; j--)
        {
            if (clicks[i].x >= windows[j].x1 && clicks[i].x <= windows[j].x2 && clicks[i].y >= windows[j].y1 && clicks[i].y <= windows[j].y2)
            {
                std::cout << windows[j].num << std::endl;
                window temp = windows[j];
                for (int k = j; k < windows_n - 1; k++)
                {
                    windows[k] = windows[k + 1];
                }
                windows[windows_n - 1] = temp;
                flag = false;
                break;
            }
        }
        if (flag)
        {
            std::cout << "IGNORED" << std::endl;
        }
    }
    return 0;
}