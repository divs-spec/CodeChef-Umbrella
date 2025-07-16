import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        FastReader sc = new FastReader();
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next();

            List<Integer> blocks = new ArrayList<>();
            int cnt = 1;

            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    cnt++;
                } else {
                    blocks.add(cnt);
                    cnt = 1;
                }
            }
            blocks.add(cnt);

            int m = blocks.size();
            int ans;
            if (m < 3) {
                System.out.println(n);
                continue;
            }

            if (m % 2 == 1) {
                ans = blocks.get(0) + blocks.get(m - 1);
            } else {
                ans = blocks.get(0) + blocks.get(m - 1) + 1;
            }

            System.out.println(ans);
        }
    }

    // Fast input class
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
    }
}
