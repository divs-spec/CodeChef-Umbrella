/*
APPROACH 1 : TLE

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
           int m = sc.nextInt();
           int a[] = new int[n*m];
           for(int i=0;i<n*m;i++)
           {
               a[i] = sc.nextInt();
           }
           Arrays.sort(a);
           if(n>m)
           {
              int res[][] = f(a,n,m);
              for(int i=0;i<n;i++)
              {
                  for(int j=0;j<m;j++)
                  {
                      System.out.print(res[i][j]+" ");
                  }
                  System.out.println();
              }
           }
           else {
              int res[][] = f(a,m,n);
              for(int i=0;i<n;i++)
              {
                  for(int j=0;j<m;j++)
                  {
                      System.out.print(res[j][i]+" ");
                  }
                  System.out.println();
              }
           }
       }
	}
	public static int[][] f(int a[], int n, int m)
	{
	    int len=  n*m;
	    int left = -1;int right = -1; int cur = Integer.MAX_VALUE;
	    int small = (m-1)/2+(m-1)%2; int large = (m-1)/2;
	    int l= 0;int r = n+m-2;
	    while(r<n*m)
	    {
	       if(l>=small&&len-1-r>=large)
	       {
	           int val = a[r]-a[l];
	           if(cur>val)
	           {
	               left = l;
	               right = r;
	               cur = val;
	           }
	       }
	       l++;r++;
	    }
	    int ans[][] = new int[n][m];
	    int ind=  left;
	    for(int j=0;j<m;j++)
	    {
	        ans[0][j] = a[ind++];
	    }
	    for(int j=1;j<n;j++)
	    {
	        ans[j][m-1] =a[ind++];
	    }
	    int ind1 = left-1; int ind2 = right+1;
	    for(int j=0;j<m-1;j++)
	    {
	        if(j%2==0)
	        {
	           ans[1][j] = a[ind1--]; 
	        }
	        else {
	           ans[1][j] = a[ind2++]; 
	        }
	    }
	    ind = 0;
	    for(int i=2;i<=n-1;i++)
	    {
	        for(int j=0;j<m-1;j++)
	        {
	           if(ind==left-small) ind = right+large+1;
	           ans[i][j] = a[ind++];
	        }
	    }
	    return ans;
	}
}

Explanation of above code : 

This Java program arranges numbers into an n x m grid in a special way. The goal is to minimize the difference between the largest and smallest number along a specific L-shaped path in the grid.

What’s the L-shape?
The L-shape is made by:

The entire top row (left to right).

The rightmost column (from second row down to last).

This path includes n + m - 1 numbers.

Step-by-Step:
Input:

For each test case, read integers n and m.

Then read n * m numbers into a 1D array a.

Sorting:

The array a is sorted. This helps in selecting the smallest possible values easily.

Orientation Handling:

If n < m, the grid is transposed (swapped). This ensures the logic always fills a grid with more rows than columns.

Finding Optimal L-path:

The program slides a window of size n + m - 1 across the sorted array.

It checks if selecting m numbers for the top row and n - 1 numbers for the right column is possible from this window.

Among valid windows, it picks the one where max - min is smallest.

Filling the Grid:

First, the top row is filled.

Then, the last column.

The second row (excluding the last column) is filled alternately from both ends of the remaining numbers.

Remaining cells are filled from leftover numbers.

Output:

If the grid was transposed, output is printed in column-major order.

Otherwise, row by row.

This method ensures the L-path (which is likely important for the problem) has the smallest value difference while keeping the rest of the grid filled appropriately.
*/

// APPROACH 2 : NO TLE 

import java.util.*;
import java.lang.*;
import java.io.*;

class codechef {
    public static Reader in;
    public static PrintWriter out;
    public static void main(String[] args) {
        out = new PrintWriter(new BufferedOutputStream(System.out));
        in = new Reader();
        int t = in.nextInt();
        while (t-- > 0)
            solve();
        out.close();
    }

    static void solve(){
        int n = in.nextInt(), m = in.nextInt();
        int k = n+m-1;
        int[][] grid = new int[n][m];
        int[] arr = in.nextIntArray(n*m);
        int smaller = Math.min(n,m)/2, bigger = (Math.min(n,m)-1) / 2;
        Arrays.sort(arr);
        int idx = smaller, dif = arr[smaller + k -1] - arr[smaller];
        for(int i = smaller+1; i + k -1 < n*m - bigger;i++){
            if (arr[i+k-1] - arr[i] < dif){
                dif = arr[i+k-1] - arr[i];
                idx = i;
            }
        }
        int l = idx, r = idx+k-1;
        int s = 0, b = n*m-1;
        if(n < m){
//            out.println("xx");
            for(int i = 0;i<n-1;i++){
                grid[i][0] = arr[l++];
                if (i % 2 ==0) grid[i][1] = arr[s++];
                else grid[i][1] = arr[b--];
            }
            for(int i = 0;i<m;i++)grid[n-1][i] = arr[l++];
        }else {
//            out.println("yy");
            for(int i = 0;i<m-1;i++){
                grid[0][i] = arr[l++];
                if (i %2 ==0) grid[1][i] = arr[s++];
                else grid[1][i] = arr[b--];
            }
            for(int i = 0;i<n;i++)grid[i][m-1] = arr[l++];
        }
        int p = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if (grid[i][j] != 0)continue;
                if (p < smaller) p = smaller;
                if (p>=idx && p<idx+k) p = idx+k;
                grid[i][j] = arr[p++];
            }
        }
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                out.print(grid[i][j] + " ");
            }
            out.println();
        }
    }

    static int qmi(long a, int b, int p) {
        long ans = 1;
        a = (a % p + p) % p;
        for (; b>0; b >>= 1) {
            if ((b & 1)!=0) ans = (a * ans) % p;
            a = (a * a) % p;
        }
        return (int)ans;
    }

    static class Reader {
        private BufferedReader br;
        private StringTokenizer st;

        Reader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] nextIntArray(int n) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = nextInt();
            return arr;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        String nextLine() {
            String s = "";
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}

/*
Explanation of the above code :

This Java code arranges numbers into an n x m grid in a special way so that a specific L-shaped path has the smallest possible range (difference between maximum and minimum values).

🔍 Problem Goal:
Given n × m integers, arrange them in a grid such that:

One "L-shaped path" has the smallest max - min difference.

The L-shape is made of either:

Top row and rightmost column (if n ≥ m), or

Left column and bottom row (if n < m)

🧩 Key Steps:
Input and Sorting:

Read n, m, and all n × m numbers into an array arr.

Sort arr to easily select the smallest ranges.

Finding the Best L-path:

The path will use k = n + m - 1 numbers.

We slide a window of size k through arr to find the one with the smallest difference between first and last element.

This window represents our optimal L-shaped path.

Grid Filling:

If n < m, the L-shape goes down the first column and across the last row.

If n ≥ m, the L-shape goes across the top row and down the last column.

Numbers from the selected window are placed along this L-path.

Filling Remaining Grid:

Fill the rest of the grid using remaining smallest and largest numbers (from both ends of arr), alternating for balance.

Remaining empty grid cells are filled with values not used in the L-path, skipping over the selected window.

Fast Input/Output:

The Reader class improves input reading performance.

Output is buffered for efficiency using PrintWriter.

✅ Output:
Finally, the code prints the entire grid with the best-balanced L-path.

This structure is useful in problems where balanced spreading of values is needed, like matrix games or puzzles.

*/
