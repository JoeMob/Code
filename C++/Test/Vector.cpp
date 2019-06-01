#include <stdio.h>
#include <iostream>
#include <math.h>
using namespace std;
class Vector3D
{
private:
    float value[3];

public:
    Vector3D(float x = 0.f, float y = 0.f, float z = 0.f)
    {
        value[0] = x, value[1] = y, value[2] = z;
    }
    float getX() const { return value[0]; }
    float getY() const { return value[1]; }
    float getZ() const { return value[2]; }
    void setX(const float x) { value[0] = x; }
    void setY(const float x) { value[1] = x; }
    void setZ(const float x) { value[2] = x; }
    float dotProduct(const Vector3D &v) const
    {
        float sum = 0.;
        for (int i = 0; i < 3; ++i)
            sum += value[i] * v.value[i];
        return sum;
    }
    Vector3D crossProduct(const Vector3D &v) const
    {
        return Vector3D(value[1] * v.value[2] - (double)value[2] * v.value[1],
                        value[2] * v.value[0] - (double)value[0] * v.value[2],
                        value[0] * v.value[1] - (double)value[1] * v.value[0]);
    }
    Vector3D &normlized()
    {
        double sum = 0.;
        for (int i = 0; i < 3; ++i)
            sum += value[i] * value[i];
        sum = sqrt(sum);
        if (fabs(sum) < 1.0e9)
            return *this;
        for (int i = 0; i < 3; ++i)
            value[i] /= sum;
        return *this;
    }
    Vector3D operator+(const Vector3D &v) const
    {
        return Vector3D(value[0] + v.value[0],
                        value[1] + v.value[1],
                        value[2] + v.value[2]);
    }
};
ostream &operator<<(ostream &out, const Vector3D &vec)
{
    printf("%.4f, %.4f, %.4f\n", vec.getX(), vec.getY(), vec.getZ());
    return out;
}
int main()
{
    float x, y, z;
    scanf("%f %f %f", &x, &y, &z);
    Vector3D v1(x, y, z);
    Vector3D v2 = v1.crossProduct(Vector3D(y, x, z));
    cout << "v1 x v2: " << v2;
    cout << "Normalized v2: " << v2.normlized();
    v1.setZ(2 * v1.getY());
    cout << v1 << v1.dotProduct(v1 + v2);
    return 0;
}