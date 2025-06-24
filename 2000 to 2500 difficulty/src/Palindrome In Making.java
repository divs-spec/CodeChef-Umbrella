import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int t = sc.nextInt();
       while(t-->0)
       {
           int n = sc.nextInt();
           int a[] = new int[n];
           for(int i=0;i<n;i++) a[i] = sc.nextInt();
           int prev = 0;
           long ans = 0L;
           for(int i=0;i<n;i++)
           {
               int d = Math.max(0,a[n-1-i]-a[i]);
               ans = ans+Math.max(0,d-prev);
               prev = d;
           }
           System.out.println(ans);
       }
	}
}
