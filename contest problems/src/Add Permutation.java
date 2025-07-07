import java.util.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] line = br.readLine().split(" ");
            int N = Integer.parseInt(line[0]);
            int K = Integer.parseInt(line[1]);

            int[] P = new int[N];

            int reverseLength = N - K + 1;
            int idx = 0;

            // First part: reverse [1, ..., reverseLength]
            for (int i = reverseLength; i >= 1; i--) {
                P[idx++] = i;
            }

            // Remaining part: normal increasing [reverseLength + 1, ..., N]
            for (int i = reverseLength + 1; i <= N; i++) {
                P[idx++] = i;
            }

            // Output the result
            for (int i = 0; i < N; i++) {
                System.out.print(P[i] + " ");
            }
            System.out.println();
        }
    }
}
