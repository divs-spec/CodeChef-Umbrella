import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    public static void main(String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();  // number of ingredients
            int[] A = new int[N];
            int[] B = new int[N];

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();  // amount needed
            }

            for (int i = 0; i < N; i++) {
                B[i] = sc.nextInt();  // amount already available
            }

            int totalCost = 0;
            for (int i = 0; i < N; i++) {
                if (A[i] > B[i]) {
                    totalCost += A[i] - B[i];
                }
            }

            System.out.println(totalCost);
        }
    }
}
