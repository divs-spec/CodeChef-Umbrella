import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static HashMap<Integer,Integer> dp[];
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int a[][] = new int[m][2];
            for(int i=0;i<m;i++)
            {
                a[i][0] = sc.nextInt();
            }
            int res[] = new int[n];
            for(int i=0;i<m;i++)
            {
                a[i][1] = sc.nextInt();
                res[a[i][0]-1] = a[i][1];
            }
            dp = new HashMap[n];
            for(int i=0;i<n;i++)
            {
                dp[i] = new HashMap<>();
            }
            int ans = f(res,n,0,0);
            System.out.println(ans);
        }
	}
	public static int f(int res[], int n, int i, int d)
	{
	    if(i==n) return 0;
	    if(dp[i].containsKey(d)) return dp[i].get(d);
	    int val = 0;
	    if(d!=-2)
	    {
	        int nd = d-1;
	        int add = 0;
	        if(res[i]==1)
	        {
	            if(nd>=1) add++;
	            else nd = 0;
	        }
	        else if(res[i]==2){
	            if(nd<=-1) add++;
	            else nd =  0;
	        }
	        val = f(res,n,i+1,nd)+add;
	       // if(i==1&&d==1) System.out.println(add+" "+nd+" "+res[i]);
	    }
	    if(d!=2)
	    {
	        int nd = d+1;
	        int add = 0;
	        if(res[i]==1)
	        {
	            if(nd>=1) add++;
	            else nd = 0;
	        }
	        else if(res[i]==2){
	            if(nd<=-1) add++;
	            else nd =  0;
	        }
	        val = Math.max(val,f(res,n,i+1,nd)+add);
	       // if(i==1&&d==1) System.out.println(add+" "+nd+" "+res[i]);
	    }
	   // System.out.println(i+" "+d+" "+val);
	    dp[i].put(d,val);
	    return val;
	}
}

/*

Explanation of the code :

This Java code solves a directional path optimization problem using dynamic programming (DP).

You are given n positions, and some have arrows:

1 means a right arrow (→)

2 means a left arrow (←)

At each position, you can move left (slope decreases by 1) or right (slope increases by 1), but the slope must stay between -2 and 2.

The goal is to maximize the number of arrows you follow correctly:

If you're moving right and see a right arrow, you earn a point.

If you're moving left and see a left arrow, you also earn a point.

If the direction doesn't match the arrow, your slope resets to 0.

The function f(res, n, i, d) is a recursive DP that tries both moves (left and right) from the current position i with slope d, and tracks the maximum points possible.

To speed things up, it memorizes answers using dp[i].get(d) so the same state isn't recomputed.

At the end, the program outputs the maximum number of matching arrows for each test case.

This approach combines recursion, memoization, and logic on movement direction to efficiently solve the problem.

*/
