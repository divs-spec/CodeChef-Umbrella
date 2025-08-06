#include <iostream>
#include <vector>
#include <string>
using namespace std;

void solve() {
    int n;
    cin >> n;
    string s;
    cin >> s;

    int m = 0;
    while ((1 << m) <= n) m++;

    string b(n, '1');
    vector<char> z(n + 1, 0);
    for (int x = n; x >= 1; --x) {
        bool f = false;
        for (int k = 0; k < m; ++k) {
            if (!((x >> k) & 1)) {
                int y = x | (1 << k);
                if (y <= n && z[y]) {
                    f = true;
                    break;
                }
            }
        }
        if (f) z[x] = 1;
        else {
            b[x - 1] = '0';
            z[x] = 1;
        }
    }

    string c(n, '0');
    vector<char> o(n + 1, 0);
    for (int x = 1; x <= n; ++x) {
        bool f = false;
        for (int k = 0; k < m; ++k) {
            if ((x >> k) & 1) {
                int y = x ^ (1 << k);
                if (o[y]) {
                    f = true;
                    break;
                }
            }
        }
        if (f) o[x] = 1;
        else {
            c[x - 1] = '1';
            o[x] = 1;
        }
    }

    int ans = 0;
    for (int i = 0; i < n; ++i) {
        if (b[i] == '0' && c[i] == '1') {
            cout << -1 << "\n";
            return;
        }
        char t = (b[i] == '0') ? '0' : ((c[i] == '1') ? '1' : s[i]);
        if (s[i] != t) ans++;
    }

    cout << ans << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    
    int Ttt;
    cin >> Ttt;
    while (Ttt--) solve();
    return 0;
}
