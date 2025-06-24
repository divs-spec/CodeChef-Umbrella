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
        int k = sc.nextInt();
        int a[] = new int[n];
        long sum = 0L;
        for(int i=0;i<n;i++)
        {
          a[i] = sc.nextInt();
          sum = sum+a[i];
        }
        PriorityQueue<pair> pq = new PriorityQueue<>((c,d)->Long.compare(d.val,c.val));
        HashSet<String> set = new HashSet<>();
        pq.offer(new pair(0,n-1,sum));
        while(k>0)
        {
            pair p = pq.poll();
            int l = p.l; int r = p.r;
            System.out.print(p.val+" ");
            if(l+1<=r)
            {
                String s = String.valueOf(l+1)+"$"+String.valueOf(r);
                if(!set.contains(s))
                {
                    set.add(s);
                    pq.offer(new pair(l+1,r,p.val-a[l]));
                }
            }
            if(r-1>=l)
            {
                String s = (l==0?"0":String.valueOf(l))+"$"+(r-1==0?"0":String.valueOf(r-1));
                if(!set.contains(s))
                {
                    set.add(s);
                    pq.offer(new pair(l,r-1,p.val-a[r]));
                }
            }
            k--;
        }
        System.out.println();
	}
}
class pair{
    long val;
    int l;
    int r;
    pair(int l, int r, long val)
    {
        this.l = l;
        this.r = r;
        this.val  = val;
    }
}
