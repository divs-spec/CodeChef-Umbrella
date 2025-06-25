import java.io.*;
import java.util.*;

public class Main {
    static final int INF_POS = 501;
    static final int MAX_TIME = 1005;

    public static int solve(BufferedReader br, StringTokenizer st) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[][] minPos = new int[MAX_TIME][N];
        for (int[] row : minPos) {
            Arrays.fill(row, INF_POS);
        }

        minPos[0][0] = A[0] - 1;

        for (int t = 0; t < MAX_TIME - 1; ++t) {
            for (int q = 0; q < N; ++q) {
                int current = minPos[t][q];
                if (current == INF_POS) continue;

                if (current == -1) return t;

                // Stay in the same queue
                int newPosStay = current - 1;
                minPos[t + 1][q] = Math.min(minPos[t + 1][q], newPosStay);

                // Move to the left queue
                if (q > 0) {
                    int leftAhead = Math.max(-1, A[q - 1] - (t + 1));
                    minPos[t + 1][q - 1] = Math.min(minPos[t + 1][q - 1], leftAhead);
                }

                // Move to the right queue
                if (q < N - 1) {
                    int rightAhead = Math.max(-1, A[q + 1] - (t + 1));
                    minPos[t + 1][q + 1] = Math.min(minPos[t + 1][q + 1], rightAhead);
                }
            }
        }

        return -1; // Shouldn't reach here
    }

    public static void main(String[] args) throws IOException {
        // Fast I/O
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            sb.append(solve(br, st)).append("\n");
        }

        System.out.print(sb);
    }
}
