#include <iostream>
using namespace std;
class Point
{
protected:
    double x, y;

public:
    Point(double a, double b) : x(a), y(b) {}
    friend ostream &operator<<(ostream &output, Point c)
    {
        output << "[" << c.x << "," << c.y << "]";
        return output;
    }
};

class Circle : public Point
{
protected:
    double r;

public:
    Circle(double a, double b, double c) : Point(a, b), r(c) {}
    double area()
    {
        return 3.14159 * r * r;
    }
    friend ostream &operator<<(ostream &output, Circle c)
    {
        output << "Center=[" << c.x << "," << c.y << "],r=" << c.r << ",area=" << c.area();
        return output;
    }
};

class Cylinder : public Circle
{
protected:
    double h;

public:
    Cylinder(double a, double b, double c, double d) : Circle(a, b, c), h(c) {}
    friend ostream &operator<<(ostream &output, Cylinder c)
    {
        output << "Center=[" << c.x << "," << c.y << "],r=" << c.r << ",h=" << c.h << "\narea=" << c.area() << ",volume=" << c.volume();
        return output;
    }
    void setHeight(const double x)
    {
        h = x;
    }
    void setPoint(const double a, const double b)
    {
        x = a;
        y = b;
    }
    void setRadius(const double a)
    {
        r = a;
    }
    double getX()
    {
        return x;
    }
    double getY()
    {
        return y;
    }
    double getRadius()
    {
        return r;
    }
    double getHeight()
    {
        return h;
    }
    double area()
    {
        return 3.14159 * r * r;
    }
    double volume()
    {
        return area() * h;
    }
};

int main()
{
    Cylinder cy1(3.5, 6.4, 5.2, 10);
    cout << "original Cylinder:\nx=" << cy1.getX() << ",y=" << cy1.getY() << ",r=" << cy1.getRadius() << ",h=" << cy1.getHeight() << "\narea=" << cy1.area() << ",volume=" << cy1.volume() << endl;
    cy1.setHeight(15);
    cy1.setPoint(5, 5);
    cy1.setRadius(7.5);
    cout << "\nnew cylinder:\n"
         << cy1;
    Point &pRef = cy1;
    cout << "\npRef as a point:" << pRef;
    Circle &cRef = cy1;
    cout << "\ncRef as a Circle:" << cRef;
    return 0;
}