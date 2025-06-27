import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
 public static void main(String[] args) throws java.lang.Exception {
  Scanner sc = new Scanner(System.in);
  int t = sc.nextInt();
  while (t-- > 0) {
   int n = sc.nextInt(), m = sc.nextInt(), c = 0;
   int a[] = new int[n];
   HashSet<Integer> s = new HashSet<>();
   for (int i = 0; i < n; i++) {
    a[i] = sc.nextInt();
    if (!s.contains(a[i])) {
     s.add(a[i]);
     System.out.print(a[i] + " ");
     c++;
    }
   }
   while (c < n) {
    HashSet<Integer> ss = new HashSet<>();
    for (int i = c; i < n; i++) {
     if (!ss.contains(a[i])) {
      ss.add(a[i]);
      System.out.print(a[i] + " ");
      c++;
     }
    }
   }
   System.out.println();
  }

 }
}
