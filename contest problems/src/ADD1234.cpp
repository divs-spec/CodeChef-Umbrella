#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int X, Y, Z;
        cin >> X >> Y >> Z;

        int p13 = min(X, Z);   // (1,3) pairs
        int p22 = Y / 2;       // (2,2) pairs

        cout << p13 + p22 << '\n';
    }
    return 0;
}
