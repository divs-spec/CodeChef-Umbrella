import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int dp[][];
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int t = sc.nextInt();
       while(t-->0)
       {
           int a = sc.nextInt();
           int b = sc.nextInt();
           int c = sc.nextInt();
           dp = new int[30][2];
           for(int i=0;i<30;i++) Arrays.fill(dp[i],-1);
           int res = f(a,b,c,0,0);
           if(res==1)
           {
               System.out.println("YES");
           }
           else {
               System.out.println("NO");
           }
       }
	}
	public static int f(int a, int b, int c, int i, int ca)
	{
	    if(i==30)
	    {
	        if(ca==0) return 1;
	        return 0;
	    }
	    if(dp[i][ca]!=-1) return dp[i][ca];
	    int bitA = (a>>i)&1;
	    int bitB = (b>>i)&1;
	    int bitC = (c>>i)&1;
	    //xbit == 0;
	    int cnt = bitA+bitB;
	    int ret = 0;
	    if(ca==0)
	    {
	       int nc = 0;
	       if(bitA==1&&bitB==1) nc = 1;
	       if(cnt%2==bitC%2)
	       {
	           ret = ret | f(a,b,c,i+1,nc);
	       }
	    }
	    else {
	       int nc = 0;
	       if(bitA==1||bitB==1) nc = 1;
	       if((cnt+ca)%2==bitC%2)
	       {
	           ret = ret | f(a,b,c,i+1,nc);
	       }
	    }
	    //xbit==1;
	    bitA = bitA^1; bitB = bitB^1; bitC = bitC^1;
	     cnt = bitA+bitB;
	    if(ca==0)
	    {
	       int nc = 0;
	       if(bitA==1&&bitB==1) nc = 1;
	       if(cnt%2==bitC%2)
	       {
	           ret = ret | f(a,b,c,i+1,nc);
	       }
	    }
	    else {
	       int nc = 0;
	       if(bitA==1||bitB==1) nc = 1;
	       if((cnt+ca)%2==bitC%2)
	       {
	           ret = ret | f(a,b,c,i+1,nc);
	       }
	    }
	    return dp[i][ca] = ret;
	}
}
