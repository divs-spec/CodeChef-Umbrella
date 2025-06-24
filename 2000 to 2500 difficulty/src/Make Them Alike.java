import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long mod = 1000000007;
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
      Scanner sc = new Scanner(System.in);
      int t = sc.nextInt();
      while(t-->0)
      {
          int n = sc.nextInt();
          int m = sc.nextInt();
          int p[] = new int[n+1];
          int a[] = new int[n+1];
          for(int i=1;i<=n;i++) p[i] = sc.nextInt();
          for(int i=1;i<=n;i++) a[i] = sc.nextInt();
          int fix = 0;
          int cnt = 0;
          int last = 0;
        //   int max[] = new int[n+1];
        int vis[] = new int[n+1];
          int flag = 0;
          for(int i=1;i<=n;i++)
          {
              int v = p[i];
              vis[v] = 1;
              if(v>=i&&a[v]>0)
              {
                  if(fix>0&&fix!=a[v])
                  {
                     flag = 1;
                     break;
                  }
                  fix = a[v];
              }
              if(vis[i]==0&&a[i]==0) cnt++;
              if(vis[i]==1&&a[i]==0) {
                  last = 1;
              }
          }
          if(flag==1) {
              System.out.println(0);
              continue;
          }
        //   System.out.println(cnt);
        //   cnt = 3;
          long ans = power((long)m,cnt);
          if(fix==0&&last==1) ans = (ans*(long)m)%mod;
          System.out.println(ans);
      }
	}
	public static long power(long a,int n)
	{
	    long res = 1L;
	    while(n>0)
	    {
	        if(n%2==0)
	        {
	            a = (a*a)%mod;
	            n=  n/2;
	        }
	        else {
	            res = (res*a)%mod;
	            n--;
	        }
	    }
	    return res;
	}
}
