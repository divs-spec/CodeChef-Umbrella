#include <bits/stdc++.h>
using namespace std;

const int INF = 1e9;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int TC;
    cin >> TC;
    while (TC--) {
        int N, M;
        cin >> N >> M;
        string S, T;
        cin >> S >> T;

        vector dp(N + 1, vector(M + 1, vector<int>(2, INF)));

        // start in "keeping" state
        dp[0][0][1] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= 1; k++) {
                    if (dp[i][j][k] == INF) continue;

                    // delete S[i]
                    if (k == 0)
                        dp[i+1][j][0] = min(dp[i+1][j][0], dp[i][j][0]);
                    else
                        dp[i+1][j][0] = min(dp[i+1][j][0], dp[i][j][1] + 1);

                    // keep S[i]
                    if (j < M && S[i] == T[j]) {
                        dp[i+1][j+1][1] = min(dp[i+1][j+1][1], dp[i][j][k]);
                    }
                }
            }
        }

        int ans = min(dp[N][M][0], dp[N][M][1]);
        cout << (ans == INF ? -1 : ans) << "\n";
    }
    return 0;
}
