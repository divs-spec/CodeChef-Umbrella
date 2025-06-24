import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[]  = new int[n];
        int max = 0;
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
            max = Math.max(max,a[i]);
        }
        int res[] = new int[max+1];
        Arrays.fill(res,n);
        int r = n; int gk = n;
        long ans = 0L;
        for(int i=n-1;i>=0;i--)
        {
            if(a[i]==k) r = Math.min(r,gk);
            if(a[i]>k) gk = i;
            if(a[i]>k) r = Math.min(r,f(res,a[i]-k,k));
            ans = ans+(r-i);
            res[a[i]] = i;
        }
        System.out.println(ans);
	}
	public static int f(int res[], int val,int k)
	{
	     int min = Integer.MAX_VALUE;
	     for(int i=1;i*i<=val;i++)
	     {
	         if(val%i==0)
	         {
	           if(i>k)  min = Math.min(min,res[i]);
	           if(val/i>k)  min = Math.min(min,res[val/i]);
	         }
	     }
	     return min;
	}
}
