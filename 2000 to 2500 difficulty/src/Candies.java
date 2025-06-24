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
          int m = sc.nextInt();
          int res[] = new int[m+1];
          for(int i=0;i<n;i++) 
          {
              int k = sc.nextInt();
              res[k]++;
          }
          int c[] = new int[m];
          for(int i=0;i<m;i++) 
          {
              c[i] = sc.nextInt();
              res[i+1]+=res[i];
          }
          long ans = 0;
          for(int i=1;i<=m;i++)
          {
              int cnt = 1; int total = 0;
              long d = 0;
              for(int j=i;j<=m;j+=i)
              {
                int r = Math.min(m,j+i-1);
                int val = res[r]-res[j-1];
                d = d+(long)val*cnt;
                cnt++;
              }
              d = d*c[i-1];
            //   System.out.println(d+" "+(i));
              ans = Math.max(ans,d);
          }
          System.out.println(ans);
      }
	}
}
