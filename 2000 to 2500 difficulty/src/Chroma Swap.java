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
            int ca[] = new int[n];
            int b[] = new int[n];
            int cb[] = new int[n];
            int res[] = new int[2*n+1];
            for(int i=0;i<n;i++) a[i] = sc.nextInt();
            for(int i=0;i<n;i++) ca[i] = sc.nextInt();
            for(int i=0;i<n;i++) b[i] = sc.nextInt();
            for(int i=0;i<n;i++) 
            {
                cb[i] = sc.nextInt();
                res[cb[i]] = 1;
            }
            PriorityQueue<Integer> pq[]  = new PriorityQueue[2*n+1];
            for(int i=0;i<=2*n;i++) {
                pq[i] = new PriorityQueue<>();
            }
            for(int i=0;i<n;i++)
            {
                pq[ca[i]].add(a[i]);
                pq[cb[i]].add(b[i]);
            }
            int max = -1; int flag = 0;
            for(int i=0;i<n;i++)
            {
                int id = ca[i];
                if(res[id]==1)
                {
                while(!pq[id].isEmpty()&&pq[id].peek()<max)
                {
                    pq[id].poll();
                }
                if(!pq[id].isEmpty()) {
                    max = pq[id].poll();
                }
                else {
                    flag = 1;
                    break;
                }
                }
                else {
                    if(a[i]<max) {
                        flag = 1;
                        break;
                    }
                    else max = a[i];
                }
                // System.out.println(max);
            }
            if(flag==1) {
                System.out.println("No");
            }
            else {
                System.out.println("Yes");
            }
        }
	}
}
