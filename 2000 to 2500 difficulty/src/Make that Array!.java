
import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
		public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner s = new Scanner(System.in);
        long t = s.nextLong();
        while (t-- > 0) {
            long n = s.nextLong();
            long[] a = new long[(int) n];
            for (int i = 0; i < n; i++) {
                a[i] = s.nextLong();
            }
            long[] b = new long[(int) n];
            for (int i = 0; i < n; i++) {
                b[i] = s.nextLong();
            }
        long points = 0;
        for (int i = 0; i < a.length; i++) {
            points += (i+1)*a[i];
        }

        long maxPoints = 0;
        for (int i = 0; i < n; i++) {
            maxPoints += b[i]*(i+1);
        }
        System.out.println(maxPoints - points); 
        }
	}
}
