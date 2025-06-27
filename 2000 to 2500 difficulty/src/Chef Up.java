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
           int c = sc.nextInt();
           int a[] = new int[n];
           for(int i=0;i<n;i++)
           {
               a[i] = sc.nextInt();
           }
           Arrays.sort(a);
           int ind = n;
           for(int i=0;i<n;i++)
           {
               if(a[i]>c)
               {
                   ind = i;
                   break;
               }
           }
           int cnt = 0;
           for(int i=ind-1;i>=0;i--)
           {
               int d = c-a[i];
               if(d%2!=0)
               {
                   cnt++;
               }
               else {
                   break;
               }
           }
           for(int i=ind;i<n;i++)
           {
               int d = a[i]-c;
                if(d%2!=0)
               {
                   cnt++;
               }
               else {
                   break;
               }
           }
           System.out.print(cnt+" ");
           if(cnt==n)
           {
               System.out.print(1);
           }
           else {
               System.out.print(-1);
           }
           System.out.println();
       }
	}
}

/*

🧠 Explanation of the Code (in Simple Words)
This Java program processes multiple test cases. For each test case:

🧩 Inputs:
n: number of elements in the array.

c: a comparison number.

a[]: array of n integers.

🚀 Goal:
Count how many elements differ oddly (odd difference) from c.

Print:

The count of such elements.

Then, print 1 if all n elements have odd difference from c, else print -1.

🔍 Step-by-Step:
Sort the array to separate values less than or greater than c.

Find ind, the index of the first number greater than c.

Count:

Elements before ind where (c - a[i]) % 2 != 0 (odd difference).

Elements after or equal to ind where (a[i] - c) % 2 != 0.

This checks how many elements have odd difference from c on both sides.

Output:

First, the count of such "odd-difference" elements.

Then, if cnt == n (i.e., all elements have odd difference from c), print 1.

Otherwise, print -1.

🧪 Example:
If a = [1, 3, 4] and c = 2,

Differences: 1 (odd), 1 (odd), 2 (even).

Only 2 elements differ oddly from 2 → cnt = 2 < 3, so output → 2 -1.

This code is likely used in a parity difference-based selection problem in a contest.

*/
