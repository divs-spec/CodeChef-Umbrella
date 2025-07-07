import java.io.*;
import java.util.*;

class Codechef {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int T = in.nextInt();
        while (T-- > 0) {
            int N = in.nextInt();
            int[] A = new int[N+1];
            int[] pos = new int[N+1];
            boolean[] hasVal = new boolean[N+1];
            for (int i = 1; i <= N; i++) {
                A[i] = in.nextInt();
                if (A[i] != 0) {
                    hasVal[A[i]] = true;
                    pos[A[i]] = i;
                }
            }
            // Build adjacency
            List<Integer>[] adj = new ArrayList[N+1];
            for (int i = 1; i <= N; i++) adj[i] = new ArrayList<>();
            for (int i = 1; i < N; i++) {
                int u = in.nextInt(), v = in.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }

            // prefix[x] = number of present values ≤ x
            int[] prefix = new int[N+1];
            for (int v = 1; v <= N; v++) {
                prefix[v] = prefix[v-1] + (hasVal[v] ? 1 : 0);
            }

            // missingSmall[k] = how many values in [1..k-1] are missing
            int[] missingSmall = new int[N+1];
            missingSmall[1] = 0;
            for (int k = 2; k <= N; k++) {
                missingSmall[k] = (k - 1) - prefix[k - 1];
            }

            // Build list of missing values
            List<Integer> missingList = new ArrayList<>();
            for (int v = 1; v <= N; v++) {
                if (!hasVal[v]) missingList.add(v);
            }
            // adjacency allows us to compute M[u], z[u] for each u
            int[] M = new int[N+1], z = new int[N+1];
            for (int u = 1; u <= N; u++) {
                int maxInit = 0, zerosCount = 0;
                for (int w : adj[u]) {
                    if (A[w] != 0) {
                        maxInit = Math.max(maxInit, A[w]);
                    } else {
                        zerosCount++;
                    }
                }
                M[u] = maxInit;
                z[u] = zerosCount;
            }

            // Case 2: k is missing initially → can go to any zero-vertex
            final int INF = N + 1;
            int minRZero = INF;
            // find all zero-vertices
            List<Integer> zeros = new ArrayList<>();
            for (int u = 1; u <= N; u++) {
                if (A[u] == 0) zeros.add(u);
            }
            // For each zero-vertex, find the minimal k that it can host
            // We need k >= M[u] and k such that missingSmall[k] >= z[u].
            // Since missingSmall is non-decreasing, we binary-search on it.
            for (int u : zeros) {
                int needZeros = z[u];
                // binary search for the smallest k in [1..N] with missingSmall[k] >= needZeros
                int lo = 1, hi = N, k2 = -1;
                while (lo <= hi) {
                    int mid = (lo + hi) >>> 1;
                    if (missingSmall[mid] >= needZeros) {
                        k2 = mid;
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
                if (k2 < 0) continue;  // cannot satisfy zero-neighbor condition
                int R = Math.max(M[u], k2);
                minRZero = Math.min(minRZero, R);
            }

            // From the missingList, pick the smallest missing k ≥ max(2, minRZero)
            int zerosCandidate = INF;
            if (minRZero < INF && !missingList.isEmpty()) {
                int target = Math.max(2, minRZero);
                int lo = 0, hi = missingList.size() - 1, pick = -1;
                while (lo <= hi) {
                    int mid = (lo + hi) >>> 1;
                    if (missingList.get(mid) >= target) {
                        pick = missingList.get(mid);
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }
                if (pick != -1) zerosCandidate = pick;
            }

            // Case 1: k is already present initially → fixed position u = pos[k]
            // we only test k ≥ 2
            int initialCandidate = INF;
            for (int k = 2; k <= N; k++) {
                if (!hasVal[k]) continue;
                int u = pos[k];
                // Check M[u] ≤ k and z[u] ≤ missingSmall[k]
                if (M[u] <= k && z[u] <= missingSmall[k]) {
                    initialCandidate = k;
                    break;
                }
            }

            int answer = Math.min(zerosCandidate, initialCandidate);
            if (answer == INF) answer = N + 1;
            out.println(answer);
        }

        out.flush();
    }
}
