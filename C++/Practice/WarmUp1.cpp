#include <iostream>
#include <string>
using namespace std;
class Person
{
protected:
    string name;
    char sex;

public:
    Person(string n = "NULL", char c = 'U') : name(n), sex(c) {}
    void setName(string n)
    {
        name = n;
    }
    void setSex(char c)
    {
        sex = c;
    }
};

class Teacher : virtual public Person
{
protected:
    string title;

public:
    Teacher(string n = "NULL", char c = 'U', string t = string("Title")) : Person(n, c), title(t) {}
};

class Student : virtual public Person
{
protected:
    double score;

public:
    Student(string n = "NULL", char c = 'U', double s = 0) : Person(n, c), score(s) {}
};

class Graduate : public Teacher, public Student
{
public:
    Graduate(string t = "NULL", double sc = 0, string n = "NULL", char c = 'U') : Person(n, c), Teacher(n, c, t), Student(n, c, sc) {}
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