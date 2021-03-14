#include <iostream>

using namespace std;

void extendedEuclid(int *d, int *x, int *y, int A, int B) {
    if (B == 0) {
        *d = A;
        *x = 1;
        *y = 0;
    }
    else {
        extendedEuclid(d, x, y, B, A%B);
        int temp = *x;
        *x = *y;
        *y = temp - (A/B)*(*y);
    }
}

int main() {

    int d, x, y;

    extendedEuclid(&d, &x, &y, 16, 10);
    cout << "gcd(16, 10) = " << d << endl;
    cout << "x, y: " << x <<  ", " << y << endl;
    return 0;
}
