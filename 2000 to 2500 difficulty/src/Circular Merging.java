import java.io.*;
import java.util.*;

 class CircularMerging {
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[2 * N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = A[i + N] = Integer.parseInt(st.nextToken());
            }
            
            // Precompute prefix sums
            long[] prefix = new long[2 * N + 1];
            for (int i = 0; i < 2 * N; i++) {
                prefix[i+1] = prefix[i] + A[i];
            }
            
            long[][] dp = new long[2 * N][2 * N];
            for (int len = 2; len <= N; len++) { // segment length
                for (int i = 0; i + len - 1 < 2 * N; i++) {
                    int j = i + len - 1;
                    dp[i][j] = Long.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + (prefix[j+1] - prefix[i]));
                    }
                }
            }
            
            long ans = Long.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                ans = Math.min(ans, dp[i][i+N-1]);
            }
            
            System.out.println(ans);
        }
    }
}
