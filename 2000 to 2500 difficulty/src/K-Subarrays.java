import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int t = sc.nextInt();
       while(t-->0)
       {
           int n = sc.nextInt();
           int k = sc.nextInt();
           int a[] = new int[n+1];
           for(int i=1;i<=n;i++) a[i] = sc.nextInt();
           long dp[][][] = new long[n+1][k+1][2];
           for(int i=0;i<=n;i++)
           {
               for(int j=0;j<=k;j++)
               {
                   Arrays.fill(dp[i][j],-1*(long)1e15);
               }
           }
           dp[0][0][0] = 0;
           for(int i=1;i<=n;i++)
           {
               for(int j=0;j<=k;j++)
               {
                     dp[i][j][0] = Math.max(dp[i-1][j][1],dp[i-1][j][0]);
                     dp[i][j][1] = dp[i-1][j][1];
                     if(j-1>=0)
                     {
                         dp[i][j][1] = Math.max(dp[i][j][1],Math.max(dp[i-1][j-1][0],dp[i-1][j-1][1]));
                     }
                    dp[i][j][1] = dp[i][j][1]+(long)j*a[i];

               }
           }
        //   System.out.println(dp[2][2][1]+" "+dp[2][2][0]);
           long ans = Math.max(dp[n][k][0],dp[n][k][1]);
           System.out.println(ans);
       }
	}
}
