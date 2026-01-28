#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int N;
        cin >> N;

        vector<int> A(N);
        for (int i = 0; i < N; i++) cin >> A[i];

        // Store positions of each value
        vector<vector<int>> pos(N + 1);
        for (int i = 0; i < N; i++) {
            pos[A[i]].push_back(i);
        }

        int ans = pos[1].size();   // all 1s are always valid
        if (ans == 0) {
            cout << 0 << '\n';
            continue;
        }

        int lastPos = pos[1][0];   // earliest 1

        for (int v = 2; v <= N; v++) {
            // find first occurrence of v after lastPos
            auto it = upper_bound(pos[v].begin(), pos[v].end(), lastPos);
            if (it == pos[v].end()) break;

            lastPos = *it;
            ans += pos[v].end() - it; // take all v's after that
        }

        cout << ans << '\n';
    }
    return 0;
}
