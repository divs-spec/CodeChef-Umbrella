#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;
    
    while(t--) {
        int n, k;
        cin >> n >> k;
        
        vector<int> b(n+1);
        for(int i = 1; i <= n; i++) cin >> b[i];
        
        // Collect S elements
        vector<int> S;
        for(int i = 1; i <= n; i++)
            if(b[i] == 1) S.push_back(i);
        
        bool ok = true;
        
        // Condition 1: all pairs in S differ by > K
        // Since S is sorted, just check adjacent elements
        for(int i = 1; i < (int)S.size(); i++) {
            if(S[i] - S[i-1] <= k) {
                ok = false;
                break;
            }
        }
        
        // Condition 2: every non-S element is within distance K of some S element
        if(ok) {
            for(int i = 1; i <= n; i++) {
                if(b[i] == 0) {
                    // Binary search for closest S element
                    auto it = lower_bound(S.begin(), S.end(), i);
                    bool covered = false;
                    if(it != S.end() && abs(*it - i) <= k) covered = true;
                    if(it != S.begin()) {
                        --it;
                        if(abs(*it - i) <= k) covered = true;
                    }
                    if(!covered) { ok = false; break; }
                }
            }
        }
        
        cout << (ok ? "Yes" : "No") << "\n";
    }
    return 0;
}
