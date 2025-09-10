import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        int T = Integer.parseInt(line.trim());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());
            int M = N * (N - 1) / 2;
            long[] w = new long[M];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens() && idx < M) {
                w[idx++] = Long.parseLong(st.nextToken());
                if (idx < M && !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            }

            Arrays.sort(w); // ascending

            long ans = 0;
            for (long k = 1; k <= N - 1; k++) {
                long pickIdx = (k - 1) * k / 2; // 0-based index
                ans += w[(int) pickIdx];
            }

            out.append(ans).append("\n");
        }

        System.out.print(out);
    }
}
