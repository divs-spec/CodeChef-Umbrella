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
            int w[] = new int[n];
            int a[] = new int[n];
            for(int i=0;i<n;i++) w[i] = sc.nextInt();
            for(int i=0;i<n;i++) a[i] = sc.nextInt();
            long front[] = new long[n];
            long back[] = new long[n];
            long sum = 0;
            for(int i=0;i<n;i++)
            {
                sum+=w[i];
                front[i] = (long)a[i]-sum;
            }
            sum = 0;
            for(int i=n-1;i>=0;i--)
            {
                sum+=w[i];
                back[i] = (long)a[i]-sum;
            }
            long res[] = new long[n];
            Deque<Integer> q = new LinkedList<>();
            for(int i=1;i<n;i++)
            {
                while(!q.isEmpty()&&front[i]>front[q.peekFirst()])
                {
                    q.pollFirst();
                }
                q.addFirst(i);
            }
            sum = 0;
            for(int i=0;i<n-1;i++)
            {
               sum+=w[i];    
               while(q.peekLast()<=i) q.pollLast();
               res[i] = Math.max(0,front[q.peekLast()]+sum);
            }
            while(!q.isEmpty()) q.pollLast();
            sum = 0;
            for(int i=n-2;i>=0;i--)
            {
                while(!q.isEmpty()&&back[i]>back[q.peekFirst()])
                {
                    q.pollFirst();
                }
                q.addFirst(i); 
            }
            for(int i=n-1;i>0;i--)
            {
                sum+=w[i];
                while(q.peekLast()>=i) q.pollLast();
                res[i] = res[i]+Math.max(0,back[q.peekLast()]+sum);
            }
            for(int i=0;i<n;i++)
            {
                long min = (long)a[i]-w[i];
                // System.out.println(res[i]+" "+i);
                min = min+Math.max(0,res[i]-a[i]);
                System.out.print(min+" ");
            }
            System.out.println();
        }
	}
}
