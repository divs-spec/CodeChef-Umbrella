#include <bits/stdc++.h>
using namespace std;

struct Treap {
    int data, priority, size;
    Treap *left, *right;
    Treap(int v, mt19937_64 &rng)
        : data(v), priority(rng()), size(1), left(nullptr), right(nullptr) {}
};

int sz(Treap* t) {
    return t ? t->size : 0;
}

void recalc(Treap* t) {
    if (t) t->size = sz(t->left) + sz(t->right) + 1;
}

void splitBySize(Treap* t, int cnt, Treap*& a, Treap*& b) {
    if (!t) {
        a = b = nullptr;
    } else if (sz(t->left) >= cnt) {
        splitBySize(t->left, cnt, a, t->left);
        b = t;
        recalc(b);
    } else {
        splitBySize(t->right, cnt - sz(t->left) - 1, t->right, b);
        a = t;
        recalc(a);
    }
}

Treap* mergeTreaps(Treap* a, Treap* b) {
    if (!a || !b) return a ? a : b;
    if (a->priority < b->priority) {
        a->right = mergeTreaps(a->right, b);
        recalc(a);
        return a;
    } else {
        b->left = mergeTreaps(a, b->left);
        recalc(b);
        return b;
    }
}

static vector<int> B;

int findRightmostMatch(Treap* t, int W, int offset) {
    if (!t || W <= 0) return 0;
    int leftSize = sz(t->left);
    int curr = offset + leftSize + 1;

    if (curr < W) {
        int res = findRightmostMatch(t->right, W, curr);
        if (res) return res;
    }

    if (curr <= W && t->data == B[curr - 1]) return curr;
    return findRightmostMatch(t->left, W, offset);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    mt19937_64 rng((unsigned)chrono::steady_clock::now().time_since_epoch().count());

    int T;
    cin >> T;
    while (T--) {
        int N, M;
        cin >> N >> M;
        vector<int> A(N);
        B.assign(M, 0);
        for (int &x : A) cin >> x;
        for (int i = 0; i < M; i++) cin >> B[i];

        Treap* root = nullptr;
        for (int x : A) {
            Treap* node = new Treap(x, rng);
            root = mergeTreaps(root, node);
        }

        int deletions = 0;
        while (true) {
            int currentSize = sz(root);
            int W = min(currentSize, M);
            int pos = findRightmostMatch(root, W, 0);
            if (!pos) break;

            Treap *t1, *t2, *t3;
            splitBySize(root, pos - 1, t1, t2);
            splitBySize(t2, 1, t2, t3);
            delete t2;
            root = mergeTreaps(t1, t3);
            deletions++;
        }

        cout << (N - deletions) << "\n";
    }

    return 0;
}
