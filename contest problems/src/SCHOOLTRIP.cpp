#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int N, X, K;
        cin >> N >> X >> K;

        if (X % K == 0) {
            cout << 0 << '\n';
        } else {
            int down = X % K;           // reduce to previous multiple
            int up = K - (X % K);       // increase to next multiple

            int ans = down;

            if (X + up <= N)
                ans = min(ans, up);

            ans = min(ans, X);          // make zero students go

            cout << ans << '\n';
        }
    }
    return 0;
}
