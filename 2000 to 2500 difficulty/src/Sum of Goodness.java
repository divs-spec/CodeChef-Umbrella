import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long fac[], pow[];
    static long mod = 1000000007;
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes 
		if(fac==null)
		{
		    fac = new long[100001];
		    pow = new long[100001];
		    fac[0] = 1; pow[0] = 1;
		    for(int i=1;i<=100000;i++)
		    {
		        fac[i] = fac[i-1]*(long)i;
		        fac[i]%=mod;
		        pow[i] = pow[i-1]*2L;
		        pow[i]%=mod;
		    }
		}
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0)
		{
		    int n = sc.nextInt();
		    int a[] = new int[n];
		    for(int i=0;i<n;i++) a[i] = sc.nextInt();
		    Arrays.sort(a);
		    long ans = 0;
		    for(int i=0;i<n;i++)
		    {
		       if(i<a[i]-1) continue; 
		       long val1 = f(i,a[i]-1);
		       long val2 = pow[n-1-i];
		       ans = ans+(val1*val2);
		       ans%=mod;
		    }
		    System.out.println(ans);
		}

	}
	public static long f(int n, int r)
	{
	    return  fac[n]*power(fac[n-r],mod-2L)%mod*power(fac[r],mod-2l)%mod;
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
	        else{
	            res = (res*a)%mod;
	            n--;
	        }
	    }
	    return res;
	}
}
