import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    static int[] getrange(int k) {
        int[] res = new int[2];
        res[0] = k + 1;
        res[1] = (int)Math.pow(2, Math.min(k, 31));
        return res;
    }
    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            List<Integer> facs = new ArrayList<>();
            int d = 1;
            while (d * d <= n) {
                if (n % d == 0) {
                    facs.add(d);
                    if (d != n / d) {
                        facs.add(n / d);
                    }
                }
                d++;
            }
            int ans = -1;
            for (int L = 1; L < 66; L++) {
                int even = L / 2;
                int odd = (L - 1) / 2;
                int[] range1 = getrange(even);
                int[] range2 = getrange(odd);
                int l1 = range1[0];
                int r1 = range1[1];
                int l2 = range2[0];
                int r2 = range2[1];
                for (int d1 : facs) {
                    if (l1 <= d1 && d1 <= r1 && l2 <= n / d1 && n / d1 <= r2) {
                        ans = L;
                        break;
                    }
                }
                if (ans != -1) {
                    break;
                }
            }
            System.out.println(ans);
        }
    }
}
