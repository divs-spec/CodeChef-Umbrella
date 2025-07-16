import java.util.*;
import java.io.*;

public class Main {

    // Declare throws IOException here
    static void solve(FastReader sc) throws IOException {
        int n = sc.nextInt();
        Long[] a = new Long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextLong();
        }

        Arrays.sort(a, Collections.reverseOrder());

        long[] pre = new long[n + 1];
        pre[0] = 0;
        for (int i = 1; i <= n; i++) {
            pre[i] = pre[i - 1] + a[i - 1];
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 1; k <= 2 * n; k++) {
            int l = (k + 1) / 2;
            int r = Math.min(n, k);

            while (l < r) {
                int m = (l + r) / 2;

                long y1 = k - m;
                long val1 = pre[m] + (y1 * (2L * m - y1 - 1)) / 2;

                long y2 = k - (m + 1);
                long val2 = pre[m + 1] + (y2 * (2L * (m + 1) - y2 - 1)) / 2;

                if (val1 < val2) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }

            long y = k - l;
            long finalVal = pre[l] + (y * (2L * l - y - 1)) / 2;
            sb.append(finalVal).append(" ");
        }

        System.out.println(sb.toString().trim());
    }

    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            solve(sc);
        }
    }

    // FastReader class
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        String nextLine() throws IOException {
            return br.readLine();
        }
    }
}
