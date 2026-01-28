import java.io.*;
import java.util.*;

public class Main {

    static final long MOD = 998244353L;

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

    static void solve(FastScanner fs, StringBuilder out) throws IOException {
        int n = fs.nextInt();
        long[] a = new long[n];

        for (int i = 0; i < n; i++) {
            a[i] = fs.nextLong();
        }

        long[] mp = new long[n + 1];
        for (long v : a) mp[(int) v]++;

        long cand = -1;
        for (int i = 1; i <= n; i++) {
            if (mp[i] >= 2) {
                cand = i;
                break;
            }
        }

        if (cand == -1) {
            out.append(1).append('\n');
            return;
        }

        // divisors of cand
        ArrayList<Long> vec = new ArrayList<>();
        for (long j = 1; j * j <= cand; j++) {
            if (cand % j == 0) {
                vec.add(j);
                long k = cand / j;
                if (j != k) vec.add(k);
            }
        }

        long res = 0;

        for (long x : vec) {
            long[] mpcur = mp.clone();
            boolean ok = true;
            long ways = 1;

            for (int i = 1; i <= n; i++) {
                if (mpcur[i] == 1) {
                    continue;
                } else if (mpcur[i] == 2) {
                    ok = false;
                    break;
                } else if (mpcur[i] > 2) {
                    if ((long) i * x > n) {
                        ok = false;
                        break;
                    }
                    int nx = (int) (i * x);
                    if (mpcur[nx] == 0) {
                        ok = false;
                        break;
                    } else if (mpcur[nx] == 1) {
                        mpcur[nx]--;
                    } else {
                        mpcur[nx]--;
                        ways = (ways * 2) % MOD;
                    }
                }
            }

            if (ok) {
                res = (res + ways) % MOD;
            }
        }

        out.append(res).append('\n');
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        StringBuilder out = new StringBuilder();

        int t = fs.nextInt();
        while (t-- > 0) {
            solve(fs, out);
        }

        System.out.print(out.toString());
    }
}
