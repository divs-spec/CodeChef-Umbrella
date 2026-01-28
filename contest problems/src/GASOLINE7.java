import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 998244353L;
    static final long INF = (long) 1e18;

    // Fast input
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ');

            boolean neg = false;
            if (c == '-') {
                neg = true;
                c = readByte();
            }

            long val = 0;
            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return neg ? -val : val;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    static long mod(long x) {
        x %= MOD;
        if (x < 0) x += MOD;
        return x;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int T = fs.nextInt();
        while (T-- > 0) {
            int n = fs.nextInt();
            int k = fs.nextInt();

            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = fs.nextLong();

            int[] prev = new int[n];
            int[] next = new int[n];

            ArrayDeque<Integer> st = new ArrayDeque<>();

            // previous smaller (>=)
            for (int i = 0; i < n; i++) {
                while (!st.isEmpty() && a[st.peek()] >= a[i]) st.pop();
                prev[i] = st.isEmpty() ? -1 : st.peek();
                st.push(i);
            }

            st.clear();

            // next smaller (>)
            for (int i = n - 1; i >= 0; i--) {
                while (!st.isEmpty() && a[st.peek()] > a[i]) st.pop();
                next[i] = st.isEmpty() ? n : st.peek();
                st.push(i);
            }

            long ans = 0;

            for (int i = 0; i < n; i++) {
                long res = 0;
                long ai = a[i] % MOD;

                int j = Math.min(next[i] - i, k);

                if (prev[i] == -1 || prev[i] + k <= i) {
                    res += (i + 1L) % MOD * ((long) j * (j - 1) / 2 % MOD) % MOD * ai;
                    res += (i + 1L) % MOD * ((n - (i + j) + 1L) % MOD) % MOD * j % MOD * ai;
                } else if (prev[i] + k >= next[i]) {
                    res += (i - prev[i]) % MOD * ((long) j * (j - 1) / 2 % MOD) % MOD * ai;
                    res += (i - prev[i]) % MOD * ((n - (i + j) + 1L) % MOD) % MOD * j % MOD * ai;
                } else {
                    res += (i + 1L) % MOD * ((long) j * (j - 1) / 2 % MOD) % MOD * ai;
                    res += (i + 1L) % MOD * ((n - (i + j) + 1L) % MOD) % MOD * j % MOD * ai;

                    int l = k - (i - prev[i]);
                    res -= (prev[i] + 1L) % MOD * ((long) l * (l - 1) / 2 % MOD) % MOD * ai;
                    res -= (prev[i] + 1L) % MOD * l % MOD * ((n - (i + l) + 1L) % MOD) % MOD * ai;
                }

                ans = mod(ans + res);
            }

            out.append(ans).append('\n');
        }

        System.out.print(out.toString());
    }
}
