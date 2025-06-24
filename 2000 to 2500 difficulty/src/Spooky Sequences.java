import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long fac[];
    static long inv[];
    static long mod = 1000000007;
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		fac = new long[500001];
		inv = new long[500001];
		fac[0] = 1L;
		for(int i=1;i<=500000;i++)
		{
		    fac[i] = fac[i-1]*i%mod;
		}
		inv[500000] = power(fac[500000],mod-2L);
		for(int i=499999;i>=0;i--)
		{
		    inv[i] = inv[i+1]*(i+1)%mod;
		}
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int m = sc.nextInt();
            dsu ds = new dsu(n);
            for(int i=0;i<m;i++)
            {
               int u = sc.nextInt()-1;
               int v = sc.nextInt()-1;
               ds.unionBySize(u,v);
            }
            int a[] = new int[n];
            for(int i=0;i<n;i++) a[i] = sc.nextInt();
            HashMap<Integer,List<Integer>> map = new HashMap<>();
            for(int i=0;i<n;i++)
            {
               int par = ds.find_par(i);
               if(!map.containsKey(par)) 
               {
                   map.put(par,new ArrayList<>());
               }
               map.get(par).add(a[i]);
            }
            long ans = 1L;
            int cur = 0;
            for(int k : map.keySet())
            {
                int len = map.get(k).size();
                long d = C(cur+len,len);
                ans = (ans*d)%mod;
                long val = f(map.get(k));
                // System.out.println(d+" "+len+" "+val);
                ans = ans*val%mod;
                cur+=len;
            }
            System.out.println(ans);
        }
	}
	public static long f(List<Integer> res)
	{
	    HashMap<Integer,Integer> map = new HashMap<>();
	    for(int i=0;i<res.size();i++)
	    {
	        map.put(res.get(i),map.getOrDefault(res.get(i),0)+1);
	    }
	    long result = 1L;
	    for(int k : map.keySet())
	    {
	        result = result*fac[map.get(k)]%mod;
	    }
	    return result;
	}
	public static long C(int n, int r)
	{
	    return fac[n]*inv[r]%mod*inv[n-r]%mod;
	}
	public static long power(long a,long n)
	{
	    long res = 1L;
	    while(n>0)
	    {
	        if(n%2==0)
	        {
	            a = (a*a)%mod;
	            n = n/2L;
	        }
	        else {
	            res = (res*a)%mod;
	            n--;
	        }
	    }
	    return res;
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
