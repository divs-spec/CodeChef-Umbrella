import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main (String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // number of test cases

        while (T-- > 0) {
            int N = sc.nextInt(); // number of marbles you have
            int M = sc.nextInt(); // total possible types
            Set<Integer> collected = new HashSet<>();

            for (int i = 0; i < N; i++) {
                collected.add(sc.nextInt());
            }

            int missingTypes = M - collected.size();
            System.out.println(missingTypes);
        }
    }
}
