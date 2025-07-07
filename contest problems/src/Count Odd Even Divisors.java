import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();  // Number of test cases
        
        while (T-- > 0) {
            int N = sc.nextInt();  // Input number
            int oddCount = 0;
            int evenCount = 0;
            
            for (int i = 1; i <= N; i++) {
                if (N % i == 0) {
                    if (i % 2 == 0)
                        evenCount++;
                    else
                        oddCount++;
                }
            }
            
            System.out.println(oddCount + " " + evenCount);
        }
        
        sc.close();
    }
}
