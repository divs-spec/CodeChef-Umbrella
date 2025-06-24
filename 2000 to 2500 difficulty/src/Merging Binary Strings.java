import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while(test-->0)
        {
            int n = sc.nextInt();
            String s = sc.next();
            String t = sc.next();
            int pre1[] = new int[n+1];
            int pre2[] = new int[n+1];
            for(int i=1;i<=n;i++)
            {
                if(s.charAt(i-1)=='0') pre1[i]=1;
                if(t.charAt(i-1)=='0') pre2[i]=1;
                pre1[i]+=pre1[i-1];
                pre2[i]+=pre2[i-1];
            }
            long dp[][] = new long[n+1][n+1];
            dp[0][0] = 0;
            for(int i=0;i<=n;i++)
            {
                for(int j=0;j<=n;j++)
                {
                    if(i==0&&j==0) continue;
                    long cnt = pre1[n]+pre2[n]-pre1[i]-pre2[j];
                    long val1 = j-1>=0?(dp[i][j-1]+(t.charAt(j-1)=='1'?cnt:0)):(long)1e15;
                    long val2 = i-1>=0?(dp[i-1][j]+(s.charAt(i-1)=='1'?cnt:0)):(long)1e15;
                    dp[i][j] = Math.min(val1,val2);
                    // if(i==1&&j==1) {
                    //     System.out.println(val1+" "+val2+" "+cnt);
                    // }
                }
            }
            // System.out.println(dp[1][1]);
            System.out.println(dp[n][n]);
        }
	}
}
