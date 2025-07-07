import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int ones = 0, twos = 0;
            for (int i = 0; i < N; i++) {
                int val = sc.nextInt();
                if (val == 1) ones++;
                else if (val == 2) twos++;
            }

            int op1 = Integer.MAX_VALUE, op2 = Integer.MAX_VALUE;

            // Convert all 1s to 2s (only possible if count of 1 is even)
            if (ones % 2 == 0) {
                op1 = ones / 2;
            }

            // Convert all 2s to 1s
            op2 = twos;

            // Pick minimum of valid options
            System.out.println(Math.min(op1, op2));
        }
    }
}
