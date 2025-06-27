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
            int a[] = new int[n];
            for(int i=0;i<n;i++) a[i] = sc.nextInt();
            int mex = 0;
            int res[] = new int[n+2];
            for(int i=0;i<n;i++)
            {
                res[a[i]]++;
                while(res[mex]>0) {
                    mex++;
                }
            }
            long dp[] = new long[n+1];
            long pre[]  = new long[n+1];
            pre[0] = 1;
            dp[0] = 1;
            int m = 0;
            int j = 1;
            res = new int[n+2];
            for(int i=1;i<=n;i++)
            {
                int val = a[i-1];
                res[val]++;
                while(j<i&&(a[j-1]>mex||res[a[j-1]]>1))
                {
                    res[a[j-1]]--;
                    j++;
                }
                while(res[m]>0) m++;
                if(mex==m)
                {
                  dp[i] = pre[j-1];
                }
                pre[i] = pre[i-1]+dp[i];
                pre[i]%=mod;
            }
            System.out.println(dp[n]);
        }
	}
}

/*
Explanation of code :

This Java program solves a problem where you're given an array and you want to split it into subarrays such that each subarray's MEX (Minimum Excluded Number) equals the MEX of the entire array.

What is MEX?
MEX is the smallest non-negative number not present in the array.
Example: For [0, 1, 3], the MEX is 2.

Goal:
Count how many ways we can split the array into contiguous parts where each part has the same MEX as the whole array.

How it works:
Calculate the overall MEX of the array using a frequency array.

Use dynamic programming (DP) and prefix sums to count valid ways to split:

dp[i]: number of valid ways to split the first i elements.

pre[i]: prefix sum of dp for fast calculations.

Use a sliding window technique to ensure:

No duplicate elements in a segment.

The MEX of the current segment matches the global MEX.

If a valid segment is found, update dp[i] with the sum of all dp[j-1] values using pre[].

Result:
Finally, dp[n] contains the total number of valid ways to split the full array.

This solution is efficient and works for large inputs due to prefix sums and optimized MEX checking.

*/
