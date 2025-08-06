import java.util.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        if (new String(arr).equals("act")) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
