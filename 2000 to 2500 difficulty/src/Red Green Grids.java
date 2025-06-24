import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long mod = 998244353;
    static long fac[],inv[];
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		fac = new long[2001];
		inv = new long[2001];
		fac[0] = 1L;
		for(int i=1;i<=2000;i++)
		{
		    fac[i] = fac[i-1]*i%mod;
		}
		inv[2000] = power(fac[2000],mod-2);
		for(int i=1999;i>=0;i--)
		{
		    inv[i] = inv[i+1]*(i+1)%mod;
		}
       Scanner sc = new Scanner(System.in);
       int t = sc.nextInt();
       while(t-->0)
       {
           int n = sc.nextInt();
           int m = sc.nextInt();
           if((n+m-1)%2!=0)
           {
               System.out.println(0);
               continue;
           }
           long val = C(n+m-2,n-1);
           val = val*C(n+m-1,(n+m-1)/2)%mod;
           int rem = (n*m)-(n+m-1);
           val = val*power(2L,(long)rem)%mod;
           System.out.println(val);
       }
	}
	public static long C(int n, int r)
	{
	    return fac[n]*inv[r]%mod*inv[n-r]%mod;
	}
	public static long power(long a, long n)
	{
	    long res = 1L;
	    while(n>0)
	    {
	        if(n%2==0)
	        {
	            a = (a*a)%mod;
	            n = n/2;
	        }
	        else{
	            res = (res*a)%mod;
	            n--;
	        }
	    }
	    return res;
	}
}
