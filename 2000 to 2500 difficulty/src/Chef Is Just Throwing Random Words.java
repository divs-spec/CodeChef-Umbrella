import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int a[] = new int[n];
            int bit[] = new int[60];
            for(int i=0;i<n;i++)
            {
                a[i] = sc.nextInt();
                for(int j=30;j>=0;j--)
                {
                    int k = (a[i]>>j)&1;
                    if(k>0) 
                    {
                        bit[j]++;
                    }
                }
            }
           long ans = 0L;
           for(int i=0;i<59;i++)
           {
               if(bit[i]>0)
               {
                   ans = ans|(1L<<i);
               }
               bit[i+1]+=bit[i]/2;
           }
           System.out.println(ans);
        }
	}
}
