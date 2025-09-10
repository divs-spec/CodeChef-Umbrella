import java.io.*;
import java.util.*;

public class Main {
    static FastScanner fs = new FastScanner(System.in);
    static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

    static void solving() {
        int n = fs.nextInt();
        String s = fs.next();
        List<Long> v = new ArrayList<>();
        for (int i = 0; i <= n; i++) v.add((long) i);

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                // Swap v[i] and v[i-1]
                long temp = v.get(i);
                v.set(i, v.get(i - 1));
                v.set(i - 1, temp);
            }
        }

        v.remove(v.size() - 1); // pop_back()

        for (long x : v) out.print(x + " ");
        out.println();
    }

    public static void main(String[] args) {
        int t = fs.nextInt();
        while (t-- > 0) solving();
        out.flush();
    }

    // Fast input reader
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
