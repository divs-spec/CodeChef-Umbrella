#include <bits/stdc++.h>

using namespace std;

#define ll long long
#define vll vector < ll >
    #define debugvec(v)\
cerr << #v << " = [ ";\
for (auto & ele: v)\
    cerr << ele << " ";\
cerr << "]" << endl;
#define debug1(x) cerr << #x << " = " << (x) << endl;
const ll MOD = 998244353;
void solve() {
    ll n;
    cin >> n;
    vll a(n);
    for (int i = 0; i < n; i++) cin >> a[i];
    ll ans = 0;
    vll mp(n + 1);
    for (auto i: a) mp[i]++;
    ll cand = -1;
    for (int i = 1; i <= n; i++) {
        if (mp[i] >= 2) {
            cand = i;
            break;
        }
    }
    //  cerr<<cand<<endl;
    if (cand == -1) {
        cout << 1 << endl;
        return;
    }
    vll vec;
    for (ll j = 1; j * j <= cand; j++) {
        if (cand % j == 0) {
            vec.push_back(j);
            ll k = cand / j;
            if (j != k)
            {
                if (cand % k == 0) {
                    vec.push_back(k);
                }
            }
        }
    }
    vll perm(n);
    iota(perm.begin(), perm.end(), 1);
    ll res = 0;
    debugvec(vec);
    for (auto x: vec) {
        vll mpcur = mp;
        bool f = true;
        ll ans = 1;
        for (int i = 1; i <= n; i++) {
            if (mpcur[i] == 1) {
                continue;
            }
            else if (mpcur[i] == 2) {
                f = false;
                break;
            }
            else {
                // is mei 0 hai means 
                // did i make i*x with it thats right
                // and 
                if (i * x > n) {
                    f = false;
                    break;
                }
                if (mpcur[i * x] == 0) {
                    f = false;
                    break;
                }
                else if (mpcur[i * x] == 1) {
                    mpcur[i * x]--;
                }
                else {
                    mpcur[i * x]--;
                    ans = (ans * 2) % MOD;
                }
            }
        }
        // cerr<<ans<<endl;
        if (f) {
            res = (res + ans) % MOD;
        }


    }

    cout << res << endl;


}
int main() {

    int t;
    cin >> t;
    while (t--)
        solve();

}
