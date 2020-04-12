#include <thread>
#include <iostream>
#include <mutex>
#include <condition_variable>

std::mutex data_mutex;
std::condition_variable data_var;
bool flag = true;

void printA()
{
    int i = 1;
    while (1)
    {
        // std::this_thread::sleep_for(std::chrono::milliseconds(10));
        std::unique_lock<std::mutex> lck(data_mutex);
        data_var.wait(lck, [] { return flag; });
        std::cout << "thread: " << std::this_thread::get_id() << "   printf: "
                  << i << std::endl;
        i += 2;
        flag = false;
        data_var.notify_one();
    }
}

void printB()
{
    int i = 2;
    while (1)
    {
        std::unique_lock<std::mutex> lck(data_mutex);
        data_var.wait(lck, [] { return !flag; });
        std::cout << "thread: " << std::this_thread::get_id() << "   printf: "
                  << i << std::endl;
        i += 2;
        flag = true;
        data_var.notify_one();
    }
}

int main()
{
    std::thread tA(printA);
    std::thread tB(printB);
    tA.join();
    tB.join();
    return 0;
}