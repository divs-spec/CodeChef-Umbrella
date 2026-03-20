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

        int cnt = 0;
        if (X >= 50) cnt++;
        if (Y >= 50) cnt++;
        if (Z >= 50) cnt++;

        if (cnt >= 2)
            cout << "Yes\n";
        else
            cout << "No\n";
    }
    return 0;
}
