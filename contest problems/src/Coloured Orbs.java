import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int R = sc.nextInt();
        int B = sc.nextInt();

        int trades = Math.min(R, B);

        int greens = trades;
        int remainingR = R - trades;
        int remainingB = B - trades;

        int skill = greens * 5 + remainingR * 1 + remainingB * 2;

        System.out.println(skill);
    }
}
