import java.util.*;

public class Codechef {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // Number of test cases

        while (T-- > 0) {
            int N = sc.nextInt();  // Number of days
            int[] A = new int[N];
            int maxA = 0;

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
                maxA = Math.max(maxA, A[i]);
            }

            int maxProfit = 0;

            for (int X = 0; X <= maxA; X++) {
                int totalSold = 0;

                for (int i = 0; i < N; i++) {
                    totalSold += Math.min(X, A[i]);
                }

                int cost = X * 30 * N;
                int revenue = totalSold * 50;
                int profit = revenue - cost;

                maxProfit = Math.max(maxProfit, profit);
            }

            System.out.println(maxProfit);
        }

        sc.close();
    }
}
