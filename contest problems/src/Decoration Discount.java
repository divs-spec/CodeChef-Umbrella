import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // Number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();  // Number of vases
            int[] A = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }

            int minCost = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;

                    int cost = A[i];
                    if (i + 1 == j) {
                        cost += A[j] / 2;
                    } else {
                        cost += A[j];
                    }
                    minCost = Math.min(minCost, cost);
                }
            }

            System.out.println(minCost);
        }
    }
}
