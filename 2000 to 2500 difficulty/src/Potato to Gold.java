// We have populated the solutions for the 10 easiest problems for your support.
// Click on the SUBMIT button to make a submission to this problem.

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
    static final long MOD = (long) 1e9 + 7;
    public static long pow(long a, long b, long mod) 
    {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a) % mod;
            }
            a = (a * a) % mod;
            b /= 2;
        }
        return res;
    }

	public static void main (String[] args) throws java.lang.Exception
	{
	    Scanner sc=new Scanner(System.in);
	    int t=sc.nextInt();
	    while(t-->0)
	    {
	        int n = sc.nextInt();
	        int k = sc.nextInt();
	        int a[] = new int[n];
	        for(int i=0; i<n; i++)
	            a[i] = sc.nextInt();
	        var hm = new HashMap<Integer, Integer>();
	        for(int i : a)
	        {
	            int r = i % k;
	            hm.put(r, hm.getOrDefault(r, 0) + 1);
	        }
	        long c = 0;
	        long xd = 0;
	        for(int i=1; i<=k/2; i++)
	        {
	            int j = k - i;
	            if(i != j)
	            {
	                long c1 = (pow(2,(long)hm.getOrDefault(i,0), MOD) - 1 + MOD) % MOD;
	                long c2 = (pow(2,(long)hm.getOrDefault(j,0), MOD) - 1 + MOD) % MOD;
	                long curr = (c1 + c2 + (c1 * xd) % MOD + (c2 * xd) % MOD) % MOD;
                    c = (c + curr) % MOD;
                    xd = (xd + curr) % MOD;
	            }
	        }
	        if (k % 2 != 0) 
	        {
                long y = hm.getOrDefault(0, 0);
                long p = (c + y * c + y) % MOD;
                System.out.println((p + 1) % MOD);
            } 
            else 
            {
                long y1 = hm.getOrDefault(0, 0);
                long y5 = hm.getOrDefault(k / 2, 0);
                long p = (c + (y1 * c) % MOD + (y5 * c) % MOD +
                         ((y1 * y5) % MOD * c % MOD) % MOD +
                         (y1 * y5) % MOD + y1 + y5) % MOD;
                System.out.println((p + 1) % MOD);
            }
        }
    }
}
