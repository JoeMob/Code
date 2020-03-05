/*
 * @lc app=leetcode id=13 lang=cpp
 *
 * [13] Roman to Integer
 */
class Solution
{
public:
    int romanToInt(string s)
    {
        int sum = 0;
        for (int i = 0; s[i] != '\0'; i++)
        {
            if (s[i] == 'M')
            {
                sum += 1000;
            }
            if (s[i] == 'D')
            {
                sum += 500;
            }
            if (s[i] == 'C' && s[i + 1] != 'D' && s[i + 1] != 'M')
            {
                sum += 100;
            }
            if (s[i] == 'C' && s[i + 1] == 'D')
            {
                sum -= 100;
            }
            if (s[i] == 'C' && s[i + 1] == 'M')
            {
                sum -= 100;
            }
            if (s[i] == 'L')
            {
                sum += 50;
            }
            if (s[i] == 'X' && s[i + 1] != 'C' && s[i + 1] != 'L')
            {
                sum += 10;
            }
            if (s[i] == 'X' && s[i + 1] == 'C')
            {
                sum -= 10;
            }
            if (s[i] == 'X' && s[i + 1] == 'L')
            {
                sum -= 10;
            }
            if (s[i] == 'V')
            {
                sum += 5;
            }
            if (s[i] == 'I' && s[i + 1] != 'V' && s[i + 1] != 'X')
            {
                sum += 1;
            }
            if (s[i] == 'I' && s[i + 1] == 'V')
            {
                sum -= 1;
            }
            if (s[i] == 'I' && s[i + 1] == 'X')
            {
                sum -= 1;
            }
        }
        return sum;
    }
};
