#include <iostream>

class Complex
{
private:
    double real;
    double unreal;

public:
    Complex()
    {
        real = 0;
        unreal = 0;
    }
    Complex(double a, double b) : real(a), unreal(b) {}
    Complex(const Complex &other) : real(other.real), unreal(other.unreal) {}
    Complex operator+(const Complex &other)
    {
        Complex answer((getReal() + other.getReal()), (getUnreal() + other.getUnreal()));
        return answer;
    }
    friend Complex operator*(const Complex &other);
    Complex &operator+=(const Complex &other)
    {
        real += other.getReal();
        unreal += other.getUnreal();
        return *this;
    }
    friend std::ostream &operator<<(const std::ostream &out, const Complex &c);
    double getReal() const
    {
        return real;
    }
    double getUnreal() const
    {
        return unreal;
    }
};

Complex operator*(const Complex &me, const Complex &other)
{
    Complex answer(((me.getReal() * other.getReal()) - (me.getUnreal() * other.getUnreal())), ((me.getReal() * other.getUnreal()) + (me.getUnreal() * other.getReal())));
    return answer;
}

std::ostream &operator<<(std::ostream &out, Complex c)
{
    out << c.getReal() << "+" << c.getUnreal() << "i\n";
    return out;
}

int main()
{
    Complex c2(2., 3.), c3 = c2 + Complex(1., 2.), c1(c2);
    Complex c4;
    c4 += c2 * c3 + c2;
    std::cout << c1 << c2 << c3 << c4;
    return 1;
}