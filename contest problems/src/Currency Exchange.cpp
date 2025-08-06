#include <bits/stdc++.h>

using namespace std;
int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int Ttt;
    cin >> Ttt;
    while (Ttt--) {
        int a, b, c, d;
        cin >> a >> b >> c >> d;

        int w1 = 5 * a + b;
        int w2 = 5 * c + d;

        cout << ((w1 >= w2 && (w1 - w2) % 6 == 0) ? "Yes\n" : "No\n");
    }
    return 0;
}
