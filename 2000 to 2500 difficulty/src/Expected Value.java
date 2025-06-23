import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long mod= 998244353;
	public static void main (String[] args) throws java.lang.Exception
	{
	
       Scanner sc = new Scanner(System.in);
       int q = 1;
       while(q-->0)
       {
           int n = sc.nextInt();
           int p = sc.nextInt();
           long invp = power((long)p,mod-2);
           long inv2 = power(2L,mod-2);
           long dp1 = 0L; long dp2 = 0L; long t = 1L; long cur = 1L; long d = inv2;
           for(int i=1;i<=n;i++)
           {
              long ndp1 = (dp1*2)%mod+(t*cur)%mod;
              ndp1%=mod;
              long ndp2 = (dp2*2)%mod+ cur*(ndp1)%mod;
              ndp2%=mod;
              dp1 = ndp1; dp2 = ndp2;
              long res = (dp2*d)%mod;
               t = (t*2)%mod;
              cur = (cur*invp)%mod;
              d = (d*inv2)%mod;
              System.out.print(res+" ");
           }
       }
	}
	public static long power(long a, long n)
	{
	    long res =1l;
	    while(n>0)
	    {
	        if(n%2==0)
	        {
	            a = (a*a)%mod;
	            n = n/2;
	        }
	        else {
	            res = (res*a)%mod;
	            n--;
	        }
	    }
	    return res;
	}
}
