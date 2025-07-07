import java.io.*;
import java.util.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            String[] parts = br.readLine().split(" ");
            long A = Long.parseLong(parts[0]);
            long B = Long.parseLong(parts[1]);

            if (A > 0 && B % A == 0)
                sb.append("Yes\n");
            else
                sb.append("No\n");
        }

        System.out.print(sb);
    }
}
