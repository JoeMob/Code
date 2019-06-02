#include <iostream>
using namespace std;

class Figure
{
public:
    virtual double getArea() = 0;
};

class Rectangle : public Figure
{
private:
    double a, b;

public:
    Rectangle(double a, double b) : a(a), b(b) {}
    double getArea()
    {
        return a * b;
    }
};

class Circle : public Figure
{
private:
    double a;

public:
    Circle(double a) : a(a) {}
    double getArea()
    {
        return a * a * 3.14159;
    }
};

bool Compare( Figure *f1,  Figure *f2)
{
    return f1->getArea() < f2->getArea();
}

int main()
{
    Rectangle rect(5, 5);
    Circle circle(5);
    if (Compare(&rect, &circle))
        std::cout << "First < Second\n";
    else
        std::cout << "First >= Second\n";
}