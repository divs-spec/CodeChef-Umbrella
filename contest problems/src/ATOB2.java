import java.util.*;

public class Main {
    static char[] s1 = new char[110];
    static char[] s2 = new char[110];

    static List<int[]> ans = new ArrayList<>();

    static void solve1(int n) {
        int i;
        int b1 = 1;

        for (i = 0; i < n; i++)
            if (s1[i] != s2[i]) b1 = 0;

        if (b1 == 1) {
            System.out.println("0");
            return;
        }

        b1 = 1;
        for (i = 0; i < n; i++)
            if (s2[i] != s2[0]) b1 = 0;

        if (b1 == 1) {
            System.out.println("1");
            return;
        }

        b1 = 1;
        for (i = 0; i < n; i++)
            if (s1[i] != s1[0]) b1 = 0;

        if (b1 == 1) System.out.println("1");
        else System.out.println("0");
    }

    static void flip(int x, int y) {
        ans.add(new int[]{x, y});
        for (int i = x; i <= y; i++) {
            s1[i] = (s1[i] == '0') ? '1' : '0';
        }
    }

    static void solve2(int n) {
        int i, j, k;
        int diff = 0;

        for (i = 0; i < n; i++) {
            if (s1[i] != s2[i]) diff++;
        }

        if (diff == 0) {
            System.out.println("0");
            return;
        }

        int b2 = 1;
        for (i = 0; i < n; i++)
            if (s2[i] != s2[0]) b2 = 0;

        int b1 = 1;
        for (i = 0; i < n; i++)
            if (s1[i] != s1[0]) b1 = 0;

        if (((b1 == 1) && (b2 == 1)) || (diff == n)) {
            System.out.println("1");
            System.out.println("1 " + n);
            return;
        }

        ans.clear();

        if (b1 == 1) {
            for (i = 0; i < n; i++)
                if (s1[i] != s2[i]) {
                    flip(i, i);
                    diff--;
                    break;
                }
        }

        while (true) {
            if (diff == 0) break;
            if ((diff == 1) && (b2 == 1)) break;

            for (i = 0; i < n; i++) {
                if (s1[i] != s2[i]) {
                    int b3 = 1;
                    for (j = 0; j < n; j++) {
                        if (j == i) continue;
                        if (s1[j] == s1[i]) b3 = 0;
                    }
                    if (b3 == 1) continue;

                    diff--;

                    if (i - 2 >= 0) {
                        j = i - 1;
                        for (k = j - 1; k >= 0; k--) {
                            if (s1[k] != s1[j]) break;
                        }
                        if (k >= 0) {
                            if (s1[j] == s1[i]) {
                                flip(k, i);
                                flip(k, j);
                            } else {
                                flip(k, j);
                                flip(k, i);
                            }
                            break;
                        }
                    }

                    if (i + 2 < n) {
                        j = i + 1;
                        for (k = j + 1; k < n; k++) {
                            if (s1[k] != s1[j]) break;
                        }
                        if (k < n) {
                            if (s1[j] == s1[i]) {
                                flip(i, k);
                                flip(j, k);
                            } else {
                                flip(j, k);
                                flip(i, k);
                            }
                            break;
                        }
                    }

                    if (s1[i - 1] == s1[i]) {
                        flip(i, i + 1);
                        flip(i - 1, i);
                        flip(i - 1, i + 1);
                    } else {
                        flip(i - 1, i);
                        flip(i, i + 1);
                        flip(i - 1, i + 1);
                    }
                    break;
                }
            }
        }

        if (b2 == 1) {
            for (i = 0; i < n; i++)
                if (s1[i] != s2[i]) {
                    flip(i, i);
                    diff--;
                    break;
                }
        }

        System.out.println(ans.size());
        for (int[] p : ans) {
            System.out.println((p[0] + 1) + " " + (p[1] + 1));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String str1 = sc.next();
            String str2 = sc.next();

            for (int i = 0; i < n; i++) {
                s1[i] = str1.charAt(i);
                s2[i] = str2.charAt(i);
            }

            solve1(n);
            solve2(n);
        }
    }
}
