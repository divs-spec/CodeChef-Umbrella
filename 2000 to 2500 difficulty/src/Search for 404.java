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
           String s = sc.next();
           long dp1[] = new long[2]; long dp2[] = new long[3];
           long dp3[] = new long[4];
           int cnt = 0;
           long p[] = new long[n+1];
           p[0] = 1L;
           for(int i=0;i<n;i++)
           {
               p[i+1] = p[i]*2%mod;
               if(s.charAt(i)=='4')
               {
                  dp1[0]++; dp1[0]%=mod;
                  for(int j=0;j<3;j++)
                  {
                      dp3[j]+=dp2[j];
                      dp3[j]%=mod;
                  }
               }
               else if(s.charAt(i)=='0')
               {
                   for(int j=0;j<2;j++)
                   {
                       dp2[j]+=dp1[j];
                       dp2[j]%=mod;
                   }
               }
               else{
                 for(int j=0;j<3;j++)
                 {
                    dp3[j+1]+=dp2[j];
                    dp3[j+1]%=mod;
                 }
                 for(int j=0;j<2;j++)
                 {
                     dp2[j+1]+=dp1[j];
                     dp2[j+1]%=mod;
                 }
                 dp1[1]++; dp1[1]%=mod;
                 cnt++;
               }
           }
           long ans = 0L;
           for(int i=0;i<=3;i++)
           {
              if(i>cnt) break; 
              ans = ans+dp3[i]*p[cnt-i]%mod;
              ans%=mod;
           }
           System.out.println(ans);
       }
	}
}
