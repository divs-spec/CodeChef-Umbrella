import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long mod = 998244353;
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
           long res = 0L; long sum = 0L; long s1 = 0L; long square = 0L;
           for(int i=0;i<n;i++)
           {
               sum  =sum+(long)a[i]*a[i]%mod*a[i]%mod*(i+1)%mod; sum%=mod;
               long res1 = s1*3L%mod*a[i]%mod*a[i]%mod;
               sum = (sum+res1)%mod; 
               sum = sum+3L*a[i]%mod*square%mod; sum%=mod;
               square  = square+(long)a[i]*a[i]%mod*(long)(i+1)%mod; square%=mod;
               square = square+2L*a[i]%mod*s1%mod;
               s1 = s1+(long)a[i]*(i+1)%mod; s1%=mod;
               res = (res+sum)%mod;
            //   System.out.println(sum);
           }
           System.out.println(res);
       }
	}
}
