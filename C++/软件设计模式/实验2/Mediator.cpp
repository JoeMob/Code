#include <iostream>
#include <string>

using namespace std;

class Country;

//Abstract Medicator
class UN
{
public:
    virtual void Declare(string message, Country *C) = 0;
};

//Abstract Colleague
class Country
{
protected:
    UN *m_UN;

public:
    Country(UN *un) : m_UN(un){};
    virtual void Declare(string Message) = 0;
    virtual void GetMessage(string Message) = 0;
};

//Colleague
class CN : public Country
{
public:
    CN(UN *un) : Country(un){};
    void Declare(string Message)
    {
        m_UN->Declare(Message, this);
    };
    void GetMessage(string Message)
    {
        cout << "CN get message: " << Message << endl;
    };
};

class US : public Country
{
public:
    US(UN *un) : Country(un){};
    void Declare(string Message)
    {
        m_UN->Declare(Message, this);
    }
    void GetMessage(string Message)
    {
        cout << "US get message: " << Message << endl;
    };
};

//Conceret Medicator
class WTO : public UN
{
private:
    Country *m_C1;
    Country *m_C2;

public:
    void SetC1(Country *C1)
    {
        m_C1 = C1;
    };
    void SetC2(Country *C2)
    {
        m_C2 = C2;
    };
    void Declare(string Message, Country *C)
    {
        if (C == m_C1)
        {
            m_C2->GetMessage(Message);
        }
        if (C == m_C2)
        {
            m_C1->GetMessage(Message);
        }
    };
};

int main()
{
    WTO *wto = new WTO();
    Country *cn = new CN(wto);
    Country *us = new US(wto);
    wto->SetC1(cn);
    wto->SetC2(us);
    cn->Declare("Fxxk off.");
    us->Declare("Fxxk off too.");
    return 0;
}