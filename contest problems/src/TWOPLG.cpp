#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int X, Y;
        cin >> X >> Y;

        if (X % 2 == 1)
            cout << "Alice\n";
        else
            cout << "Bob\n";
    }
    return 0;
}
