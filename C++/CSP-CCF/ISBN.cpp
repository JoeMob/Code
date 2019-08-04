#include <iostream>
#include <cstring>

int main()
{
    char ISBN_in[14] = {'\0'};
    std::cin >> ISBN_in;
    int rec = 0;
    rec = (ISBN_in[0] - '0' + (ISBN_in[2] - '0') * 2 + (ISBN_in[3] - '0') * 3 + (ISBN_in[4] - '0') * 4 + (ISBN_in[6] - '0') * 5 + (ISBN_in[7] - '0') * 6 + (ISBN_in[8] - '0') * 7 + (ISBN_in[9] - '0') * 8 + (ISBN_in[10] - '0') * 9) % 11;
    // std::cout << rec << std::endl;
    if (rec == (ISBN_in[12] - '0'))
    {
        std::cout << "Right" << std::endl;
    }
    else if (rec == 10 && ISBN_in[12] == 'X')
    {
        std::cout << "Right" << std::endl;
    }
    else
    {
        if (rec < 10)
        {
            ISBN_in[12] = '0' + rec;
        }
        if (rec == 10)
        {
            ISBN_in[12] = 'X';
        }
        std::cout << ISBN_in << std::endl;
    }
}