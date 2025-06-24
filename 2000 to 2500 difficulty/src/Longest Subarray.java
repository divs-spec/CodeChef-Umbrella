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
           int a[] = new int[n];
           for(int i=0;i<n;i++)
           {
               a[i] = sc.nextInt();
           }
           int p[][] = new int[n][30];
           int s[][] = new int[n][30]; 
           for(int i=0;i<n;i++)
           {
               for(int j=0;j<30;j++)
               {
                   int bit = (a[i]>>j)&1;
                   p[i][j]+=bit;
                   if(i>0) {
                       p[i][j]+=p[i-1][j];
                   }
               }
              
           }
           for(int i=n-1;i>=0;i--)
           {
               for(int j=0;j<30;j++)
               {
                   int bit = (a[i]>>j)&1;
                   s[i][j]+=bit;
                   if(i<n-1){
                       s[i][j]+=s[i+1][j];
                   }
               }
           }
           int ans = -1;
           for(int i=0;i<n;i++)
           {
               int val = f(a,p,s,n,i);
               ans = Math.max(ans,val);
           }
           System.out.println(ans);
       }
	}
	public static int f(int a[], int p[][], int s[][],int n, int i)
	{
	     int low = 0; int high = i; int ans = -1;
	     while(low<=high)
	     {
	         int mid = (low+high)/2;
	         int val = helper(a,p,s,mid,i,n);
	         if(val==0)
	         {
	            low = mid+1;
	         }
	         else if(val==1)
	         {
	             high = mid-1;
	         }
	         else {
	            ans = i-mid+1;
	            high = mid-1;
	         }
	     }
	     return ans;
	}
	public static int helper(int a[], int p[][], int s[][],int start, int  end, int n)
	{
	    int flag = 2;
	    for(int j=0;j<30;j++)
	    {
	        int cnt1 = p[end][j]-(start-1>=0?p[start-1][j]:0);
	        int cnt2 = p[n-1][j]-cnt1;
	        if(cnt1>0&&cnt2==0) return 0;
	        if(cnt1==0&&cnt2>0) {
	            flag = 1;
	        }
	    }
	    return flag;
	}
}
