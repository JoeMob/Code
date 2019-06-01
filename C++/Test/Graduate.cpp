#include <iostream>
#include <string>
#include <stdio.h>
using namespace std;
class Person
{
protected:
    string name;
    char sex;

public:
    Person(const string &n = string("NULL"), char s = 'U') : name(n), sex(s){};
    void setName(const string &n)
    {
        name = n;
    }
    void setSex(char s)
    {
        sex = s;
    }
};
class Teacher : virtual public Person
{
protected:
    string title;

public:
    Teacher(const string &n = string("NULL"), char s = 'U', const string &t = string("Title")) : Person(n, s), title(t){};
};
class Student : virtual public Person
{
protected:
    double score;

public:
    Student(const string &n = string("NULL"), char s = 'U', double sc = 0) : Person(n, s), score(sc){};
};
class Graduate : public Teacher, public Student
{
public:
    Graduate(const string &t = string("Undefined"), double sc = 0, const string &n = string("NULL"), char s = 'U') : Teacher(n, s, t), Student(n, s, sc), Person(n, s){};
    void show()
    {
        cout << "name:" << name << "\n";
        cout << "sex:" << sex << "\n";
        cout << "title:" << title << "\n";
        cout << "score:" << score << "\n";
    }
};
int main()
{
    Graduate grad1("Assistance", 89.5);
    grad1.show();
    printf("\n");
    grad1.setName("Wang LiLi");
    grad1.setSex('M');
    grad1.show();
    return 0;
}