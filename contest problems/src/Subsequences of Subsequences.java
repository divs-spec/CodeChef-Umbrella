import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007L;

    // Helper functions
    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    static long binexp(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1) res = (res % MOD * a % MOD) % MOD;
            a = (a % MOD * a % MOD) % MOD;
            b >>= 1;
        }
        return res;
    }

    static long inv(long x) {
        return binexp(x, MOD - 2);
    }

    // Core solve function
    static void solve(FastScanner fs, PrintWriter out) {
        int n = fs.nextInt();
        int k = fs.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        if (n == k) {
            long x = 0;
            for (long y : a) x ^= y;
            out.println(1 + (x != 0 ? 1 : 0));
            return;
        }

        if (k % 2 == 0) {
            for (int i = 0; i < n; i++) {
                a[i] |= (1L << 30);
            }
        }

        long[] bs = new long[32];
        for (int i = 0; i < n; i++) {
            long cur = a[i];
            for (int j = 0; j < 32; j++) {
                if (((cur >> j) & 1L) == 1L) {
                    if (bs[j] == 0) {
                        bs[j] = cur;
                        break;
                    } else {
                        cur ^= bs[j];
                    }
                }
            }
        }

        int c = 0;
        for (int i = 0; i < 32; i++) {
            if (bs[i] != 0) c++;
        }

        out.println(1L << (c - (k % 2 == 0 ? 1 : 0)));
    }

    public static void main(String[] args) throws IOException {
        FastScanner fs = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int t = 1;
        t = fs.nextInt();

        while (t-- > 0) {
            solve(fs, out);
        }

        out.flush();
    }

    // Fast input class
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
