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

        vector<long long> A(N), B(N);
        for (auto &x : A) cin >> x;
        for (auto &x : B) cin >> x;

        bool ok = true;
        long long prefMax = -1;

        for (int i = 0; i < N; i++) {
            if (A[i] <= prefMax) {
                // cannot increment this position at all
                if (B[i] != A[i]) {
                    ok = false;
                    break;
                }
            } else {
                // can increment freely, but not decrease
                if (B[i] < A[i]) {
                    ok = false;
                    break;
                }
            }
            prefMax = max(prefMax, A[i]);
        }

        cout << (ok ? "Yes\n" : "No\n");
    }
    return 0;
}
