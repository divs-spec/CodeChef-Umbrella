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
          String s = sc.next();
          int n = s.length();
          long ans = 0; long sum  = 0; long max = 0;
          for(int i=0;i<n;i++)
          {
              if(s.charAt(i)=='1') {
                  ans = ans+(long)(i+1)*(n-i);
                  sum = sum-(long)(i+1)*(n-i);
              }
              else {
                  sum = sum+(long)(i+1)*(n-i);
              }
              if(sum<0) {
                  sum = 0;
              }
              max = Math.max(max,sum);
          }
          System.out.println(ans+max);
      }
	}
}
