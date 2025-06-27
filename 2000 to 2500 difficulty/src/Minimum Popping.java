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
           int a[] = new int[m];
           for(int i=0;i<m;i++)
           {
               a[i] = sc.nextInt();
           }
           int back[] = new int[n+1];
           int cnt = 0; int r = -1;
           for(int i=m-1;i>=0;i--)
           {
               int k = a[i];
               if(back[k]==0) cnt++;
               back[k]++;
               if(cnt==n)
               {
                   r = i;
                   break;
               }
           }
           int front[] = new int[n+1];
           int ans = m-r;
           for(int i=0;i<m;i++)
           {
               int k = a[i];
               front[k]++;
               while(r<m&&(front[a[r]]>0||back[a[r]]>1))
               {
                   back[a[r]]--;
                   r++;
               }
               ans = Math.min(ans,i+1+m-r);
           }
           System.out.println(ans);
       }
	}
}

/*
Explanation of the code : 

This Java code solves the problem of finding the shortest subarray to remove so that the remaining array has all elements from 1 to n exactly once.

🧠 Problem Setup:
You're given an array a[] of length m, filled with values between 1 and n.

The goal is to remove the smallest number of elements (as a continuous subarray), so that the remaining array is a complete set: every number from 1 to n appears exactly once.

🔍 How It Works:
Suffix preparation (back[]):

Scans from right to left to find the smallest suffix (ending part) where all n numbers appear at least once.

r becomes the starting index of this suffix.

Prefix processing (front[]):

Goes from left to right, trying to remove a prefix and shrink the remaining suffix further.

Keeps track of seen elements in the front and adjusts the suffix start r so that:

There are no duplicate elements

All n unique values are still covered.

Answer calculation:

At each point, the minimal total number of elements removed from start and end is tracked.

✅ Output:
The program prints the minimum number of elements to remove from the array to meet the condition.

It uses two-pointer sliding window logic and frequency maps for an efficient solution.

*/
