#include <bits/stdc++.h>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int t;
    cin >> t;

    while(t--){
        int n;
        cin >> n;
        vector<long long> a(n);
        for(auto& x : a) cin >> x;

        vector<int> L(n), R(n);
        stack<int> st;

        for(int i = 0; i < n; i++){
            while(!st.empty() && a[st.top()] < a[i]) st.pop();
            L[i] = st.empty() ? -1 : st.top();
            st.push(i);
        }
        while(!st.empty()) st.pop();

        for(int i = n-1; i >= 0; i--){
            while(!st.empty() && a[st.top()] < a[i]) st.pop();
            R[i] = st.empty() ? n : st.top();
            st.push(i);
        }
        while(!st.empty()) st.pop();

        // Sparse table for range max
        int LOG = 1;
        while((1<<LOG) <= n) LOG++;
        vector<vector<int>> sparse(LOG, vector<int>(n));
        for(int i = 0; i < n; i++) sparse[0][i] = a[i];
        for(int j = 1; j < LOG; j++)
            for(int i = 0; i+(1<<j)-1 < n; i++)
                sparse[j][i] = max(sparse[j-1][i], sparse[j-1][i+(1<<(j-1))]);

        auto qmax = [&](int l, int r) -> long long {
            if(l > r) return 0LL;
            int k = __lg(r-l+1);
            return max((long long)sparse[k][l], (long long)sparse[k][r-(1<<k)+1]);
        };

        long long ans = 0;

        for(int i = 0; i < n; i++){
            int l = L[i]; // nearest greater on left (-1 if none)
            int r = R[i]; // nearest greater on right (n if none)

            // -------------------------------------------------------
            // Case 1: left dominator l is the max of [L,R]
            //   Must include l, exclude r.
            //   R in [i, r-1]  -- r-1 is already correct (r = first > a[i] on right)
            //   L in [l2+1, l] where l2 = first index < l with val > a[i]
            //                       = first index in (L[l], l) from the left side
            //   Elements in (L[l], l) are all < a[l]. Among them, first > a[i] is l2.
            //   Binary search in (L[l], l) for first pos where suffix-max > a[i].
            // -------------------------------------------------------
            if(l != -1){
                int ll = L[l]; // next greater than a[l] on left of l; ll < l
                // Find l2 = last index in (ll, l) with a[index] > a[i], 
                // i.e., first index from LEFT in (ll+1, l-1) where prefix... 
                // Actually: we want the RIGHTMOST position p in (ll, l) = [ll+1, l-1]
                // such that a[p] > a[i], because L must be > l2 (we need L > l2 means L >= l2+1).
                // Wait: l2 = last index < l with value > a[i].
                // In range [ll+1, l-1]: if any element > a[i] exists, 
                //   l2 = rightmost such element, then L in [l2+1, l].
                // If none: l2 = ll, then L in [ll+1, l].
                
                int l2;
                if(ll+1 > l-1 || qmax(ll+1, l-1) <= a[i]){
                    l2 = ll;
                } else {
                    // Find rightmost pos in [ll+1, l-1] with val > a[i]
                    int lo = ll+1, hi = l-1;
                    while(lo < hi){
                        int mid = (lo+hi+1)/2;
                        if(qmax(mid, l-1) > a[i]) lo = mid;
                        else hi = mid-1;
                    }
                    l2 = lo;
                }
                long long lc = l - (l2+1) + 1;
                long long rc = (r-1) - i + 1;
                if(lc > 0 && rc > 0) ans += a[i] * lc * rc;
            }

            // -------------------------------------------------------
            // Case 2: right dominator r is the max of [L,R]
            //   Must include r, exclude l.
            //   L in [l+1, i]  -- l+1 is already correct (l = first > a[i] on left)
            //   R in [r, r2-1] where r2 = first index > r with val > a[i]
            //   Elements in (r, R[r]) are all < a[r]. Among them, first > a[i] is r2.
            // -------------------------------------------------------
            if(r != n){
                int rr = R[r];
                int r2;
                if(r+1 > rr-1 || qmax(r+1, rr-1) <= a[i]){
                    r2 = rr;
                } else {
                    int lo = r+1, hi = rr-1;
                    while(lo < hi){
                        int mid = (lo+hi)/2;
                        if(qmax(r+1, mid) > a[i]) hi = mid;
                        else lo = mid+1;
                    }
                    r2 = lo;
                }
                long long lc = i - (l+1) + 1;
                long long rc = (r2-1) - r + 1;
                if(lc > 0 && rc > 0) ans += a[i] * lc * rc;
            }
        }

        cout << ans << "\n";
    }
    return 0;
}
