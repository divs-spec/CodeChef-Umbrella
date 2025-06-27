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
           dsu ds = new dsu(n);
           long ans = 0;
           for(int bit=0;bit<=30;bit++)
           {
               int u = -1;
              for(int i=0;i<n;i++)
              {
                  if(((1<<bit)&a[i])>0)
                  {
                      if(u==-1)
                      {
                          u = i;
                      }
                      else {
                         if(ds.find_par(i)!=ds.find_par(u))
                         {
                             ds.unionBySize(i,u);
                             ans = ans+(1<<bit);
                         }
                      }
                  }
              }
           }
           if(ds.size[ds.find_par(0)]!=n)
           {
               System.out.println(-1);
           }
           else {
               System.out.println(ans);
           }
       }
	}
}
class dsu{
    int parent[];
    int rank[];
    int size[];
    dsu(int n)
    {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        for(int i=0;i<n;i++)
        {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }
    public int find_par(int k)
    {
        if(k==parent[k]) return k;
       return parent[k] = find_par(parent[k]);
    }
    public void unionByRank(int x,int y)
    {
        int px = find_par(x);
        int py = find_par(y);
        if(px==py) return;
        if(rank[px]>rank[py])
        {
            parent[py] = px;
        }
        else if(rank[py]>rank[px])
        {
            parent[px] = py;
        }
        else
        {
            parent[py] = px;
            rank[px]++;
        }
    }
    public void unionBySize(int x,int y)
    {
        int px = find_par(x);
        int py = find_par(y);
        if(px==py) return;
        if(size[px]>=size[py])
        {
            parent[py] = px;
            size[px] = size[px]+size[py];
        }
        else
        {
            parent[px] = py;
            size[py] = size[py]+size[px];
        }
    }
}
