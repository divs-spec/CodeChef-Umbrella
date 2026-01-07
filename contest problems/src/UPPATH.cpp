#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

void solve() {
    int N;
    if (!(cin >> N)) return;
    vector<int> a(N), b(N);
    for (int i = 0; i < N; ++i) cin >> a[i];
    for (int i = 0; i < N; ++i) cin >> b[i];

    // pref[i] = the minimum possible value we can have at cell (1, i)
    // such that a non-decreasing path exists from (1, 0) to (1, i)
    vector<int> pref(N, 2e9); 
    pref[0] = min(a[0], b[0]);

    for (int i = 1; i < N; ++i) {
        int v1 = min(a[i], b[i]);
        int v2 = max(a[i], b[i]);
        
        // Try to pick the smaller value first to keep pref[i] as small as possible
        if (v1 >= pref[i-1]) pref[i] = v1;
        else if (v2 >= pref[i-1]) pref[i] = v2;
        else break; // No value in this column is >= previous value
    }

    // suff[i] = the maximum possible value we can have at cell (2, i)
    // such that a non-decreasing path exists from (2, i) to (2, N-1)
    vector<int> suff(N, -1);
    suff[N-1] = max(a[N-1], b[N-1]);

    for (int i = N - 2; i >= 0; --i) {
        int v1 = min(a[i], b[i]);
        int v2 = max(a[i], b[i]);

        // Try to pick the larger value first to keep suff[i] as large as possible
        if (v2 <= suff[i+1]) suff[i] = v2;
        else if (v1 <= suff[i+1]) suff[i] = v1;
        else break; // No value in this column is <= next value
    }

    bool possible = false;
    for (int k = 0; k < N; ++k) {
        // We can transition at column k if:
        // 1. A valid non-decreasing path reached (1, k)
        // 2. A valid non-decreasing path exists from (2, k) to the end
        // 3. We can pick values x, y from {a[k], b[k]} such that pref[k-1] <= x <= y <= suff[k+1]
        
        if (pref[k] != 2e9 && suff[k] != -1) {
            int v1 = min(a[k], b[k]);
            int v2 = max(a[k], b[k]);
            
            // Can we pick Row 1 value as 'top' and Row 2 value as 'bottom' 
            // such that top <= bottom AND top >= pref_prev AND bottom <= suff_next?
            // Actually, the simplest check: is there ANY way to pick x, y from {a[k], b[k]}
            // such that x >= pref[k-1] (if k>0) and y <= suff[k+1] (if k<N-1) and x <= y?
            
            int p_val = (k == 0) ? -1 : pref[k-1];
            int s_val = (k == N-1) ? 2e9 : suff[k+1];

            // Case 1: row1 = v1, row2 = v2
            if (v1 >= p_val && v2 <= s_val && v1 <= v2) possible = true;
            // Case 2: row1 = v2, row2 = v1
            if (v2 >= p_val && v1 <= s_val && v2 <= v1) possible = true;
        }
        if (possible) break;
    }

    cout << (possible ? "Yes" : "No") << "\n";
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int T;
    if (!(cin >> T)) return 0;
    while (T--) {
        solve();
    }
    return 0;
}
