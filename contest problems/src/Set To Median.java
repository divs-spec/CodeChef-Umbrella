import java.io.*;
import java.util.*;

public class Main {
    static class Kushwant {} // Dummy class to match your C++ class kushwant{}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            long[] a = new long[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Long.parseLong(st.nextToken());
            }
            Arrays.sort(a);

            long[] p = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = p[i - 1] + a[i - 1];
            }

            long s = p[n];
            long m = 0;

            for (int j = 0; j < n;) {
                long v = a[j];
                int l = j;
                int h = j;
                while (h + 1 < n && a[h + 1] == v) h++;

                int f = h - l + 1;
                int q = l;
                int r = n - h - 1;

                int u = Math.min(q, f - 1);
                long d = 0;
                if (u > 0) d = (long) u * v - p[u];
                m = Math.max(m, d);

                int o = Math.min(r, q - f + 1);
                if (o > 0) {
                    int b = 1, c = o, e = 0;
                    while (b <= c) {
                        int g = (b + c) / 2;
                        if (a[f + g - 2] + a[h + g] <= 2 * v) {
                            e = g;
                            b = g + 1;
                        } else {
                            c = g - 1;
                        }
                    }
                    if (e > 0) {
                        int k = e + f - 1;
                        long x = p[k];
                        int y = h + 1;
                        int z = h + e;
                        long w = p[z + 1] - p[y];
                        long dd = (long) k * v - x + (long) e * v - w;
                        m = Math.max(m, dd);
                    }
                }
                j = h + 1;
            }

            out.append(s + Math.max(0, m)).append("\n");
        }
        System.out.print(out);
    }
}
