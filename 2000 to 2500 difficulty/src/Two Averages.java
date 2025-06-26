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
           int m = sc.nextInt();
           int k = sc.nextInt();
           long sa = 0; long sb = 0;
           for(int i=0;i<n;i++)
           {
               int  el = sc.nextInt();
               sa+=el;
           }
           for(int i=0;i<m;i++)
           {
               int el = sc.nextInt();
               sb+=el;
           }
           long val1 = sa*m; long val2 =sb*n;
           if(k==1&&n==sa&&m==sb)
           {
               System.out.println(-1);
               continue;
           }
           int ans = 0;
           while(val1<=val2)
           {
               long d1 = sa-n;
               long d2 = (long)k*m-sb;
               if(d1>=d2)
               {
                   val1 = val1+sa;
                   val2 = val2+n;
                   m++;
                   sb++;
               }
               else {
                   val1 = val1+(long)k*m;
                   val2 = val2+sb;
                   n++;
                   sa+=k;
               }
               ans++;
           }
             System.out.println(ans);
       }
	}
}
