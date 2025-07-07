import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        
        int A = sc.nextInt();  // Length of rectangle
        int B = sc.nextInt();  // Breadth of rectangle
        int C = sc.nextInt();  // Side of square
        
        int rectArea = A * B;
        int squareArea = C * C;
        
        if (rectArea == squareArea) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        
        sc.close();
    }
}
