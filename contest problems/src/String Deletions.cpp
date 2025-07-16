#include <bits/stdc++.h>
using namespace std;

#define int long long
#define pb push_back
#define fr(i,a,b) for(int i = a; i < b; i++)
#define add(v) accumulate((v).begin(), (v).end(), 0LL)
#define all(x) (x).begin(), (x).end()
#define sz(a) (int)a.size()
#define fast ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
typedef vector<int> vi;
#define in(v,n)  vi v(n); fr(i,0,n) cin>>v[i];

// ==================== Solve Function ====================
void solve() {
    int t; cin >> t;
    while(t--) {
        int n; cin >> n;
        string s; cin >> s;

        vi blocks; 
        int cnt = 1;
        fr(i,1,n){
            if(s[i] == s[i-1]) {
                cnt++;
            } else {
                blocks.pb(cnt);
                cnt = 1;
            }
        }
        blocks.pb(cnt);

        int m = blocks.size();
        int ans;
        if(m<3){
            cout<<n<<endl;
            continue;
        }
        if (m % 2 == 1) {
         ans = blocks[0] + blocks[m - 1];
        } else {
        ans = blocks[0] + blocks[m - 1] + 1;
    }
   cout<<ans<<endl;
    }
}



int32_t main() {
    fast;
    solve();
    return 0;
}

