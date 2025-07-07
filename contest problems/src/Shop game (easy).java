import java.util.*;
import java.io.*;
class Codechef {
    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String[] nk = br.readLine().split(" ");
            int N = Integer.parseInt(nk[0]);
            int K = Integer.parseInt(nk[1]);

            String[] costStr = br.readLine().split(" ");
            int[] cost = new int[N];
            for (int i = 0; i < N; i++) {
                cost[i] = Integer.parseInt(costStr[i]);
            }

            String A = br.readLine();

            if (A.charAt(K - 1) == '1') {
                // Flip A and reverse cost array
                A = flipBinary(A);
                reverse(cost);
            }

            // Count trailing '0's
            int c = 0;
            for (int i = K - 1; i >= 0; i--) {
                if (A.charAt(i) == '0') c++;
                else break;
            }

            int blocked = K - c + 1;

            // Pair cost with index
            List<int[]> items = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                items.add(new int[]{cost[i], i});
            }

            items.sort((a, b) -> b[0] - a[0]); // Sort by cost descending

            Set<Integer> topKIndexes = new HashSet<>();
            long total = 0;
            for (int i = 0; i < K; i++) {
                topKIndexes.add(items.get(i)[1]);
                total += items.get(i)[0];
            }

            // Check if all of blocked region is present in topK
            boolean needSwap = true;
            for (int i = N - blocked; i < N; i++) {
                if (!topKIndexes.contains(i)) {
                    needSwap = false;
                    break;
                }
            }

            if (needSwap) {
                // Find smallest cost in blocked
                int minBlocked = Integer.MAX_VALUE, minBlockedIdx = -1;
                for (int i = N - blocked; i < N; i++) {
                    if (topKIndexes.contains(i) && cost[i] < minBlocked) {
                        minBlocked = cost[i];
                        minBlockedIdx = i;
                    }
                }

                // Find largest remaining outside topK
                int maxOutside = Integer.MIN_VALUE;
                for (int i = 0; i < N; i++) {
                    if (!topKIndexes.contains(i)) {
                        maxOutside = Math.max(maxOutside, cost[i]);
                    }
                }

                total = total - minBlocked + maxOutside;
            }

            System.out.println(total % MOD);
        }
    }

    static String flipBinary(String A) {
        StringBuilder sb = new StringBuilder();
        for (char ch : A.toCharArray()) {
            sb.append(ch == '0' ? '1' : '0');
        }
        return sb.toString();
    }

    static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }
}
