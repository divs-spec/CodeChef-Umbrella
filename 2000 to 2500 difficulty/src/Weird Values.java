import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int t  = sc.nextInt();
       while(t-->0)
       {
           int n = sc.nextInt();
           int a[] = new int[n];
           int max = 0;
           for(int i=0;i<n;i++)
           {
               a[i] = sc.nextInt();
               max = Math.max(max,a[i]);
           }
           int next[] = new int[n];
           int res[] = new int[max+1];
           Arrays.fill(res,n);
           for(int i=n-1;i>=0;i--)
           {
               next[i] = res[a[i]];
               res[a[i]] = i;
           }
           long ans = 0;
           for(int i=0;i<n;i++)
           {
               if(res[a[i]]!=-1)
               {
                   res[a[i]]=-1;
                   ans = ans+f(next,n,a[i],i);
               }
           }
           System.out.println(ans);
       }
	}
	public static long f(int next[], int n, int val, int cur)
	{
	     int prev = -1;
	     int i = cur;
	     int cnt = val-1;
	     while(cnt>0&&i!=n)
	     {
	         i = next[i];
	         cnt--;
	     }
	     long ans = 0;
	     while(i<n)
	     {
	         ans = ans+(long)(cur-prev)*(next[i]-i)*val;
	         i = next[i];
	         prev = cur;
	         cur = next[cur];
	     }
	     return ans;
	}
}
