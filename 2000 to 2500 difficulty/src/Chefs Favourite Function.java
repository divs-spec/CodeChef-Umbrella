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
           int l = sc.nextInt();
           int r = sc.nextInt();
           int k = 31-Integer.numberOfLeadingZeros(r);
           if(l==r)
           {
               System.out.println(f(l)+g(r));
               continue;
           }
           int n = 1<<k;
           if(n>=l)
           {
           int ans = f(n)+g(n);
           System.out.println(ans);
           }
           else {
             int ans = 0;  
             int cur = (1<<k);  
             for(int i=k-1;i>=0;i--)
             {
                int bit1 = (l>>i)&1;
                int bit2 = (l>>(i+1))&1;
                if(bit1>0&&bit2==0)
                {
                   cur = cur^(1<<(i+1));
                 if(cur<=r) 
                 {
                    int val = f(cur)+g(cur);
                    //  System.out.println(cur+" "+val);
                    ans = Math.max(ans,val);
                 }
                   cur = cur^(1<<(i+1));
                   
                }
                if(bit1>0) cur = cur|(1<<i);
             }
             ans = Math.max(ans,f(l)+g(l));
             System.out.println(ans);
           }
        //   System.out.println(g(2)+" "+g(12)+" "+f(10)+" "+f(32));
       }
	}
	public static int f(int n)
	{
	    if(n==1) return 0;
	    if(n%2==0) return f(n/2)+1;
	    return f(n/2);
	}
	public static int g(int n)
	{
	    if(n==1) return 1;
	    if(n%2==0) return 2*g(n/2)+1;
	    return 2*g(n/2);
	}
}
