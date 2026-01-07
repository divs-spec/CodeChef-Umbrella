#include <bits/stdc++.h>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;
    while (T--) {
        int N;
        string S;
        cin >> N >> S;

        if (N == 1) {
            cout << "Yes\n";
            continue;
        }

        vector<int> freq(26, 0);
        for (char c : S) freq[c - 'a']++;

        bool ok = false;
        for (int f : freq) {
            if (f >= 2) {
                ok = true;
                break;
            }
        }

        cout << (ok ? "Yes\n" : "No\n");
    }

    return 0;
}
