import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int L = sc.nextInt(); // Number of left shoes
        int R = sc.nextInt(); // Number of right shoes
        
        // The missing shoes are equal to the absolute difference
        int missing = Math.abs(L - R);
        
        System.out.println(missing);
    }
}
