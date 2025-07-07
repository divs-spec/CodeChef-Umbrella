import java.io.*;
import java.util.*;

public class Main {
    static final long INF = (long) 1e18;
    static int[][] par = new int[2001][2001];

    static class HLD {
        int n, cur;
        int[] siz, top, dep, parent, in, out, seq;
        List<Integer>[] adj;

        HLD(int n) {
            this.n = n;
            siz = new int[n];
            top = new int[n];
            dep = new int[n];
            parent = new int[n];
            in = new int[n];
            out = new int[n];
            seq = new int[n];
            adj = new ArrayList[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            cur = 0;
        }

        void addEdge(int u, int v) {
            adj[u].add(v);
            adj[v].add(u);
        }

        void work(int root) {
            top[root] = root;
            dep[root] = 0;
            parent[root] = -1;
            dfs1(root);
            dfs2(root);
        }

        void dfs1(int u) {
            if (parent[u] != -1) {
                adj[u].remove((Integer) parent[u]);
            }
            siz[u] = 1;
            for (int i = 0; i < adj[u].size(); i++) {
                int v = adj[u].get(i);
                parent[v] = u;
                dep[v] = dep[u] + 1;
                dfs1(v);
                siz[u] += siz[v];
                if (siz[v] > siz[adj[u].get(0)]) {
                    Collections.swap(adj[u], 0, i);
                }
            }
        }

        void dfs2(int u) {
            in[u] = cur;
            seq[cur++] = u;
            for (int v : adj[u]) {
                top[v] = (v == adj[u].get(0) ? top[u] : v);
                dfs2(v);
            }
            out[u] = cur;
        }

        int lca(int u, int v) {
            while (top[u] != top[v]) {
                if (dep[top[u]] > dep[top[v]]) {
                    u = parent[top[u]];
                } else {
                    v = parent[top[v]];
                }
            }
            return dep[u] < dep[v] ? u : v;
        }

        boolean isAncestor(int u, int v) {
            return in[u] <= in[v] && out[v] <= out[u];
        }

        int rootedSize(int u, int v) {
            if (u == v) return n;
            if (!isAncestor(v, u)) return siz[v];
            return n - siz[par[u][v]];
        }
    }

    static void solve(BufferedReader br) throws IOException {
        int n = Integer.parseInt(br.readLine());
        HLD hld = new HLD(n);
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            String[] parts = br.readLine().split(" ");
            int u = Integer.parseInt(parts[0]) - 1;
            int v = Integer.parseInt(parts[1]) - 1;
            hld.addEdge(u, v);
            g[u].add(v);
            g[v].add(u);
        }
        hld.work(0);

        for (int s = 0; s < n; s++) {
            final int src = s;
            dfsParent(g, src, -1, src);
        }

        long[][] dp = new long[n][n];
        for (long[] row : dp) Arrays.fill(row, -1);

        long ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, calc(i, j, hld, dp, g, n));
            }
        }
        System.out.println(ans);
    }

    static void dfsParent(List<Integer>[] g, int u, int p, int s) {
        par[s][u] = p;
        for (int v : g[u]) {
            if (v != p) dfsParent(g, v, u, s);
        }
    }

    static long calc(int x, int y, HLD hld, long[][] dp, List<Integer>[] g, int n) {
        if (dp[x][y] != -1) return dp[x][y];
        if (x == y) {
            long res = 1L * n * (n + 1) / 2;
            for (int u : g[x]) {
                int sz = hld.rootedSize(x, u);
                res -= 1L * sz * (sz + 1) / 2;
            }
            return dp[x][y] = res;
        }
        int px = par[y][x];
        int py = par[x][y];
        int sz1 = hld.rootedSize(y, x);
        int sz2 = hld.rootedSize(x, y);
        long res1 = calc(px, y, hld, dp, g, n) + 1L * sz1 * sz2;
        long res2 = calc(x, py, hld, dp, g, n) + 1L * sz1 * sz2;
        return dp[x][y] = Math.max(res1, res2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Tc = Integer.parseInt(br.readLine());
        while (Tc-- > 0) {
            solve(br);
        }
    }
}
