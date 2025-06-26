import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st;
        
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            
            TreeSet<Integer> posts = new TreeSet<>();
            posts.add(0);
            posts.add(m);

            TreeMap<Integer, Integer> lengths = new TreeMap<>();
            lengths.put(m, 1);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; ++i) {
                int x = Integer.parseInt(st.nextToken());
                
                int L = posts.floor(x);
                int R = posts.ceiling(x);

                lengths.put(R - L, lengths.get(R - L) - 1);
                if (lengths.get(R - L) == 0) {
                    lengths.remove(R - L);
                }

                lengths.put(x - L, lengths.getOrDefault(x - L, 0) + 1);
                lengths.put(R - x, lengths.getOrDefault(R - x, 0) + 1);
                
                posts.add(x);
                pw.print(lengths.lastKey() + " ");
            }
            pw.println();
        }
        pw.close();
        br.close();
    }
}
