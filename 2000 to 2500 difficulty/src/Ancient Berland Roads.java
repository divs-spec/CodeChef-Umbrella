import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();
       int m = sc.nextInt();
       int q = sc.nextInt();
       int a[] = new int[n];
       for(int i=0;i<n;i++)
       {
           a[i] = sc.nextInt();
       }
       int edges[][] = new int[m][2];
       for(int i=0;i<m;i++)
       {
           edges[i][0] = sc.nextInt()-1;
           edges[i][1] = sc.nextInt()-1;
       }
       int queries[][] = new int[q][];
       int vis[] = new int[m];
       for(int i=0;i<q;i++)
       {
           char ch = sc.next().charAt(0);
           if(ch=='D')
           {
               int k = sc.nextInt()-1;
               queries[i] = new int[]{k};
               vis[k] = 1;
           }
           else {
               int town = sc.nextInt()-1;
               int pop = sc.nextInt();
               queries[i] = new int[]{town,a[town]};
               a[town] = pop;
           }
       }
       dsu ds = new dsu(n,a);
      List<Long> ans = new ArrayList<>();
      for(int i=0;i<m;i++)
      {
          if(vis[i]==0)
          {
              int u = edges[i][0]; int v = edges[i][1];
              ds.unionBySize(u,v);
          }
      }
      for(int i=q-1;i>=0;i--)
      {
          long res = ds.tree[0];
          ans.add(res);
          if(queries[i].length==2)
          {
             int town = queries[i][0]; int pop = queries[i][1];
             int d = pop-a[town];
             int par = ds.find_par(town);
             ds.a[par]+=d;
             ds.update(0,n-1,par,ds.a[par],0);
             a[town] = pop;
          }
          else {
              int k = queries[i][0];
              int u = edges[k][0]; int v = edges[k][1];
              ds.unionBySize(u,v);
          }
      }
      for(int i=q-1;i>=0;i--)
      {
          System.out.println(ans.get(i));
      }
	}
}
class dsu{
    int parent[];
    int size[];
    long a[];
    long tree[];
    int n;
    dsu(int n, int a[])
    {
        this.n = n;
        parent = new int[n];
        size = new int[n];
        this.a = new long[n];
        tree = new long[4*n];
        for(int i=0;i<n;i++)
        {
            parent[i] = i;
            size[i] = 1;
            this.a[i]= a[i];
            update(0,n-1,i,a[i],0);
        }
    }
    public int find_par(int k)
    {
        if(k==parent[k]) return k;
       return parent[k] = find_par(parent[k]);
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
            a[px]+=a[py];
            update(0,n-1,px,a[px],0);
            update(0,n-1,py,0,0);
        }
        else
        {
            parent[px] = py;
            size[py] = size[py]+size[px];
            a[py]+=a[px];
            update(0,n-1,py,a[py],0);
            update(0,n-1,px,0,0);
        }
    }
    public void update(int l, int r,int i,long val,int ind)
    {
        if(l==r)
        {
            tree[ind] = val;
            return;
        }
        int m = (l+r)/2;
        if(m>=i) update(l,m,i,val,2*ind+1);
        else update(m+1,r,i,val,2*ind+2);
        tree[ind] = Math.max(tree[2*ind+1],tree[2*ind+2]);
    }
}
