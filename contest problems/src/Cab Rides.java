import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine().trim());

            int fullCabs = N / 4;
            int rem = N % 4;

            int cost = fullCabs * 400;
            if (rem == 1 && fullCabs > 0) {
                // instead of (4 + 1), do (3 + 2)
                cost -= 400;   // remove one cab of 4
                cost += 300;   // add cab of 3
                cost += 200;   // add cab of 2
            } else if (rem == 1) {
                cost += 200; // if no full cab, just 1 cab
            } else if (rem == 2) {
                cost += 200;
            } else if (rem == 3) {
                cost += 300;
            }

            out.append(cost).append("\n");
        }

        System.out.print(out);
    }
}
