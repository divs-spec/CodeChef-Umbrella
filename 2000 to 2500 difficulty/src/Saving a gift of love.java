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
            int x = sc.nextInt();
            int n = sc.nextInt();
            long high = 1L;
            long a[][] = new long[n][2];
            for(int i=0;i<n;i++)
            {
                a[i][0] = sc.nextLong();
                a[i][1] = sc.nextLong();
                high = high+a[i][1];
            }
            int m = sc.nextInt();
            long b[][] = new long[m][3];
            for(int i=0;i<m;i++)
            {
                b[i][0] = sc.nextLong();
                b[i][1] = sc.nextLong();
                b[i][2] = sc.nextLong();
            }
            long low = 1L; long ans = high;
            while(low<=high)
            {
                long mid = low+(high-low)/2L;
                if(f(a,b,n,m,mid))
                {
                    ans=  mid;
                    high = mid-1L;
                }
                else {
                    low = mid+1L;
                }
            }
            System.out.println(ans);
        }
	}
	public static boolean f(long a[][], long b[][],int n, int m,long val)
	{
	     int i = 0; int j = 0;
	     while(i<n&&j<m)
	     {
	         if(a[i][0]<b[j][0])
	         {
	             val = val-a[i][1];
	             i++;
	         }
	         else {
	             if(val>=b[j][1])
	             {
	                 val+=b[j][2];
	             }
	             j++;
	         }
	         if(val<1) return false;
	     }
	     while(i<n)
         {
             val=  val-a[i][1];
             if(val<1) return false;
             i++;
         }
	     return true;
	}
}
