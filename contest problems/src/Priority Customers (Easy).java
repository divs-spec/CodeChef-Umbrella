import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 998244353;

    public static void main(String[] args) throws IOException {
        // Fast I/O setup
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long[] a = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }

            // Precompute powers of 2 modulo MOD
            long[] po = new long[n + 1];
            po[0] = 1;
            for (int i = 1; i <= n; i++) {
                po[i] = (po[i - 1] * 2) % MOD;
            }

            int ind = n;
            boolean[] vis = new boolean[n + 2]; // +2 to avoid bounds error on vis[x]

            if (a[n - 1] != 1) {
                ind = n - 1;
                vis[(int)a[ind]] = true;
                for (int i = n - 1; i > 0; i--) {
                    if (a[i] > a[i - 1] && a[i - 1] != 1) {
                        ind = i - 1;
                        vis[(int)a[ind]] = true;
                    } else {
                        break;
                    }
                }
            }

            long ans = po[n - ind];
            int x = 1;
            for (int i = 0; i < ind; i++) {
                if (i == ind - 1) {
                    ans = (ans + po[n - ind]) % MOD;
                    break;
                }

                vis[(int)a[i]] = true;

                while (x <= n && vis[x]) {
                    if (ind < n && a[ind] == x) {
                        ind++;
                        vis[x] = false;
                        break;
                    }
                    x++;
                }

                ans = (ans + po[n - ind]) % MOD;
            }

            out.println(ans);
        }

        out.flush();
        out.close();
    }
}
