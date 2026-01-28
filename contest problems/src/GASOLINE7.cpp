#include <bits/stdc++.h>
using namespace std;
#define int long long
typedef __int128_t ell;
#define all(x) (x).begin(), (x).end()
#define sz(x) (int)(x).size()
#define both(x) (x).first>>(x).second
#define set_bits __builtin_popcountll
#define sec second
#define fir first
#define newline "\n"
#define _ << " " <<
#define pb push_back

const int MOD = 998244353;
const int MOD1 = 1000000007;
const int INF = 1e18;

int32_t main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    int T = 1;
    cin >> T;
    while (T--) {
        int n, k;
        cin >> n >> k;
        vector<int> a(n);
        for (int i = 0; i < n; i++) cin >> a[i];

        vector<int> next(n), prev(n);
        stack<int> st;

        for (int i = 0; i < n; i++) {
            while (!st.empty() && a[st.top()] >= a[i]) st.pop();
            prev[i] = (st.empty() ? -1 : st.top());
            st.push(i);
        }

        while (!st.empty()) st.pop();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.empty() && a[st.top()] > a[i]) st.pop();
            next[i] = (st.empty() ? n : st.top());
            st.push(i);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            int res = 0;
            int ai = a[i] % MOD;

            if (prev[i] == -1 || prev[i] + k <= i) {
                int j = min(next[i] - i, k);
                res = (res + (i + 1) % MOD * ((j * (j - 1) / 2) % MOD) % MOD * ai) % MOD;
                res = (res + (i + 1) % MOD * ((n - (i + j) + 1) % MOD) % MOD * (j % MOD) % MOD * ai) % MOD;
            }
            else if (prev[i] + k >= next[i]) {
                int j = min(next[i] - i, k);
                res = (res + (i - prev[i]) % MOD * ((j * (j - 1) / 2) % MOD) % MOD * ai) % MOD;
                res = (res + (i - prev[i]) % MOD * ((n - (i + j) + 1) % MOD) % MOD * (j % MOD) % MOD * ai) % MOD;
            }
            else {
                int j = min(next[i] - i, k);
                res = (res + (i + 1) % MOD * ((j * (j - 1) / 2) % MOD) % MOD * ai) % MOD;
                res = (res + (i + 1) % MOD * ((n - (i + j) + 1) % MOD) % MOD * (j % MOD) % MOD * ai) % MOD;

                int l = k - (i - prev[i]);
                res = (res - (prev[i] + 1) % MOD * ((l * (l - 1) / 2) % MOD) % MOD * ai) % MOD;
                res = (res - (prev[i] + 1) % MOD * (l % MOD) % MOD * ((n - (i + l) + 1) % MOD) % MOD * ai) % MOD;
            }

            ans = (ans + res) % MOD;
        }

        if (ans < 0) ans += MOD;
        cout << ans << newline;
    }
    return 0;
}
