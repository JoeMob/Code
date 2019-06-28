#include <iostream>
using namespace std;

class Point2D
{
private:
    double x, y;

public:
    Point2D(double a, double b) : x(a), y(b) {}
    double getX()
    {
        return x;
    }
    double getY()
    {
        return y;
    }
    friend ostream &operator<<(ostream &out, Point2D p)
    {
        out << p.getX() << " " << p.getY();
        return out;
    }
};

class Circle : public Point2D
{
private:
    double radius;

public:
    Circle(double a, double b, double c) : Point2D(a, b), radius(c) {}
    Circle(Point2D a, double b) : Point2D(a), radius(b) {}
    friend ostream &operator<<(ostream &out, Circle c)
    {
        out << "Center:" << c.getCenter() << endl;
        out << "Radius:" << c.radius << endl;
        return out;
    }
    Point2D getCenter()
    {
        return Point2D(getX(), getY());
    }
    double getRadius()
    {
        return radius;
    }
    void show()
    {
        cout << getX() << "," << getY() << "," << radius << endl;
    }
};

int main()
{
    Circle c1(1, 2, 3);
    c1.show();
    cout << c1;
    Circle c2(Point2D(1, 2), 3);
    c2.show();
    cout << c2;
    return 0;
}