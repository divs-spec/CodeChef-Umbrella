import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int spf[];
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        if(spf==null)
        {
            spf = new int[100001];
            for(int i=0;i<=100000;i++) {
                spf[i] = i;
            }
            spf[1] = 1;
            for(int i=2;i<=100000;i++)
            {
                if(spf[i]==i)
                {
                    for(int j=i;j<=100000;j+=i)
                    {
                        if(spf[j]==j)
                        {
                            spf[j] = i;
                        }
                    }
                }
            }
        }
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int n = sc.nextInt();
            int a[] = new int[n];
            for(int i=0;i<n;i++) a[i] = sc.nextInt();
            HashMap<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                int val = a[i];
                while(val>1)
                {
                    int d = spf[val];
                    map.put(d,i);
                    while(val>1&&val%d==0)
                    {
                        val = val/d;
                    }
                }
            }
            int max = 0;
            for(int i=0;i<n;i++)
            {
                int val = a[i];
                while(val>1)
                {
                    int d = spf[val];
                    max = Math.max(max,map.get(d));
                    while(val>1&&val%d==0)
                    {
                        val = val/d;
                    }
                }
                if(max==i) break;
            }
            System.out.println(max+1);
        }
	}

}
