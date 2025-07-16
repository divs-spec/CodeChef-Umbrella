#include <bits/stdc++.h>
using namespace std;
#define ll long long
#define fastio() ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL)

void solve() {
    ll n;
    cin >> n;
    vector<ll> a(n);
    for (ll i = 0; i < n; i++) {
        cin >> a[i];
    }

    sort(a.begin(), a.end(), greater<ll>());

    vector<ll> pre(n + 1);
    pre[0] = 0;
    for (ll i = 1; i <= n; i++) {
        pre[i] = pre[i - 1] + a[i - 1];
    }

    for (ll k = 1; k <= 2 * n; k++) {
        ll l = (k + 1) / 2;
        ll r = min(n, k);
        ll ans = 0;

        while (l < r) {
            ll m = (l + r) / 2;

            ll y1 = k - m;
            ll val1 = pre[m] + (y1 * (2 * m - y1 - 1)) / 2;

            ll y2 = k - (m + 1);
            ll val2 = pre[m + 1] + (y2 * (2 * (m + 1) - y2 - 1)) / 2;

            if (val1 < val2) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        ll y = k - l;
        ll final_val = pre[l] + (y * (2 * l - y - 1)) / 2;
        cout << final_val << " ";
    }
    cout << endl;
}

int main() {
    fastio();
    ll t;
    cin >> t;
    while (t--) {
        solve();
    }
    return 0;
}
