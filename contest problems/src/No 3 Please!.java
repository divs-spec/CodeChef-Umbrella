import java.util.*;

class Codechef {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }

            if (isGood(A)) {
                System.out.println("Yes");
                continue;
            }

            boolean possible = false;

            for (int i = 1; i <= N; i++) {
                if (isGoodAfterReversingPrefix(A, i)) {
                    possible = true;
                    break;
                }
            }

            System.out.println(possible ? "Yes" : "No");
        }
    }

    static boolean isGood(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
            if (sum % 3 == 0)
                return false;
        }
        return true;
    }

    static boolean isGoodAfterReversingPrefix(int[] arr, int k) {
        int N = arr.length;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int val;
            if (i < k) {
                val = arr[k - 1 - i]; // reverse prefix
            } else {
                val = arr[i];
            }
            sum += val;
            if (sum % 3 == 0)
                return false;
        }
        return true;
    }
}
