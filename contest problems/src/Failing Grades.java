import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();  // number of tests
            int[] scores = new int[N];
            int sum = 0;
            boolean failed = false;

            for (int i = 0; i < N; i++) {
                scores[i] = sc.nextInt();
                sum += scores[i];
                double avg = (double) sum / (i + 1);
                if (avg < 40) {
                    failed = true;
                    // break early, but still read remaining inputs
                }
            }

            if (failed)
                System.out.println("No");
            else
                System.out.println("Yes");
        }
    }
}
