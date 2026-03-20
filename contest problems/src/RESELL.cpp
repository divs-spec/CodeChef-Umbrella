#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, K;
        cin >> N >> K;

        vector<int> A(N);
        for (int i = 0; i < N; i++)
            cin >> A[i];

        // take highest resale values first
        sort(A.begin(), A.end(), greater<int>());

        long long profit = 0;

        for (int i = 0; i < N; i++) {
            if (i < K) {
                if (A[i] > 5)
                    profit += A[i] - 5;
            } else {
                if (A[i] > 10)
                    profit += A[i] - 10;
            }
        }

        cout << profit << '\n';
    }
    return 0;
}
