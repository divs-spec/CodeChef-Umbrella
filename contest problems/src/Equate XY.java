import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // Fast I/O setup
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (x == y) {
                out.println(0);
                continue;
            }

            long ans = 2 * c;

            // Ensure x < y
            if (x > y) {
                long temp = x;
                x = y;
                y = temp;
            }

            // Check divisibility condition
            if (y % x == 0) {
                for (long i = 1; i * i <= y; i++) {
                    if (y % i == 0) {
                        if (i % x == 0) {
                            ans = Math.min(ans, c + Math.abs(i - z));
                        }
                        long div = y / i;
                        if (div % x == 0) {
                            ans = Math.min(ans, c + Math.abs(div - z));
                        }
                    }
                }
            }

            out.println(ans);
        }

        out.flush();
    }
}
