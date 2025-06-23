import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long dp[][];
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int t = sc.nextInt();
       while(t-->0)
       {
           int n = sc.nextInt();
           int l[] = new int[n];
           int r[] = new int[n];
           for(int i=0;i<n;i++) l[i] = sc.nextInt();
           for(int i=0;i<n;i++) r[i] = sc.nextInt();
           dp = new long[n][2];
           for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
           long ans = f(l,r,n,0,1);
           System.out.println(ans);
       }
	}
	public static long f(int l[], int r[], int n, int i, int d)
	{
	    if(i==n-1)
	    {
	        if(d==0) return (long)l[i];
	        return 0;
	    }
	    if(dp[i][d]!=-1) return dp[i][d];
	    long val = Long.MAX_VALUE;
	    if(i==0)
	    {
	       long val1 = f(l,r,n,i+1,0);
	       long val2 = f(l,r,n,i+1,1)+(long)r[i];
	       val =  Math.min(val1,val2);
	    }
	   else if(d==0)
	    {
	        long val1 = f(l,r,n,i+1,0)+(long)l[i];
	        long val2 = f(l,r,n,i+1,1)+(long)l[i]+(long)r[i];
	        val =  Math.min(val1,val2);
	    }
	    else 
	    {
	        long val1 = f(l,r,n,i+1,0);
	        long val2 = f(l,r,n,i+1,1)+(long)r[i];
	        val =  Math.min(val1,val2);
	    }
	   
	    return  dp[i][d] = val;
	}
}
