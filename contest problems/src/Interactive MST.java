
import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 998244353;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = temp[0], m = temp[1];
            boolean[][] ls = new boolean[n][n];
            boolean[][] ps = new boolean[n][n];
            int tots = 0;
            for (int i = 0; i < m; i++) {
                int[] edge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int u = edge[0] - 1, v = edge[1] - 1, w = edge[2];
                ls[u][v] = ls[v][u] = true;
                if (w == 0) {
                    ps[u][v] = ps[v][u] = true;
                    tots++;
                }
            }
            boolean[] trees;
            int[] val;
            int full = 1 << n;
            trees = new boolean[full];
            val = new int[full];

            for (int mask = 1; mask < full; mask++) {
                int vcnt = Integer.bitCount(mask);
                int ecnt = 0, zcnt = 0;
                for (int u = 0; u < n; u++) {
                    if ((mask & (1 << u)) == 0) continue;
                    for (int v = u + 1; v < n; v++) {
                        if ((mask & (1 << v)) != 0 && ls[u][v]) {
                            ecnt++;
                            if (ps[u][v]) zcnt++;
                        }
                    }
                }
                if (ecnt != vcnt - 1) continue;
                int r0 = Integer.numberOfTrailingZeros(mask);
                boolean[] vis = new boolean[n];
                Deque<Integer> dq = new ArrayDeque<>();
                dq.add(r0);
                vis[r0] = true;
                int seen = 1;
                while (!dq.isEmpty()) {
                    int u = dq.poll();
                    for (int v = 0; v < n; v++) {
                        if (((mask & (1 << v)) != 0) && ls[u][v] && !vis[v]) {
                            vis[v] = true;
                            dq.add(v);
                            seen++;
                        }
                    }
                }
                if (seen == vcnt) {
                    trees[mask] = true;
                    val[mask] = 2 * zcnt + 1;
                }
            }

            int[] dp = new int[full];
            Arrays.fill(dp, -1);
            dp[0] = 0;

            for (int mask = 1; mask < full; mask++) {
                int anchor = Integer.numberOfTrailingZeros(mask);
                for (int sub = mask; sub > 0; sub = (sub - 1) & mask) {
                    if (((sub & (1 << anchor)) != 0) && trees[sub] && dp[mask ^ sub] >= 0) {
                        dp[mask] = Math.max(dp[mask], dp[mask ^ sub] + val[sub]);
                    }
                }
            }
            System.out.println(tots + n - dp[full - 1]);
        }
    }
}
