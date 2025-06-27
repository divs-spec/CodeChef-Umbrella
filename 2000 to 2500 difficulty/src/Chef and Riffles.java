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
            int k = sc.nextInt();
            int a[] = new int[n+1];
            int b[] = new int[n+1];
            for(int i=1;i<=n;i++)
            {
                a[i]  = i;
            }
            int j = 2;
            for(int i=3;i<n;i+=2)
            {
                b[j++] = a[i];
            }
            for(int i=2;i<n;i+=2)
            {
              b[j++] = a[i];
            }
            int vis[] = new int[n+1];
            int ans[] = new int[n+1];
            ans[1] = 1; ans[n] = n;
            for(int i=2;i<n;i++)
            {
                if(vis[i]==0)
                {
                    List<Integer> res = new ArrayList<>();
                    dfs(i,vis,res,b);
                    int len = res.size();
                    for(j=0;j<len;j++)
                    {
                        int come = (j+k)%len;
                        ans[res.get(j)] = res.get(come);
                    }
                }
            }
            for(int i=1;i<=n;i++)
            {
                System.out.print(ans[i]+" ");
            }
            System.out.println();
        }
	}
	public static void dfs(int i, int vis[],List<Integer> res,int b[])
	{
	    if(vis[i]==1) return;
	    vis[i] = 1;
	    res.add(i);
	    dfs(b[i],vis,res,b);
	}
}
