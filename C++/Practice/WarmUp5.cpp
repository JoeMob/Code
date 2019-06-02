#include <iostream>
using namespace std;

class Rectangle
{
private:
    double width, height;

public:
    Rectangle(double w = 0., double h = 0.) : width(w), height(h) {}
    double getArea()
    {
        return width * height;
    }
    friend bool operator<(Rectangle r1, Rectangle r2)
    {
        if (r1.getArea() < r2.getArea())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
};

template <typename T>
void insertSort(T *ptr, int size)
{
    for (int i = 1; i < size; ++i)
    {
        for (int j = i; j > 0; - j)
        {
            if (ptr[j] < ptr[j - 1])
            {
                T temp = ptr[j];
                ptr[j] = ptr[j - 1];
                ptr[j-1] = temp;
            }
            else
                break;
        }
    }
}

int main()
{
    Rectangle data[4];
    data[0] = Rectangle(1, 2);
    data[1] = Rectangle(5, 7);
    data[2] = Rectangle(2, 8);
    data[3] = Rectangle(3, 4);
    for (int i = 0; i < 4; ++i)
    {
        printf("%f,", data[i].getArea());
    }
    printf("\n");
    insertSort(data, 4);
    for (int i = 0; i < 4; ++i)
    {
        printf("%f,", data[i].getArea());
    }
    return 1;
}