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
            String s = sc.next();
            int ind =-1;
            List<Integer> res = new ArrayList<>();
            for(int i=0;i<n;i++)
            {
                if(i>0&&s.charAt(i)!=s.charAt(i-1)) {
                    ind = i;
                }
                int val = s.charAt(i)-'0';
                res.add(val);
            }
            if(ind==-1)
            {
                System.out.println(n+" "+0);
                continue;
            }
            List<int[]> ans = new ArrayList<>();
            while(res.size()>1)
            {
                int a[] = f(res);
                ans.add(a);
                List<Integer> res2 = new ArrayList<>();
                for(int i=0;i<a[0];i++) res2.add(res.get(i));
                res2.add(a[2]);
                for(int i=a[1]+1;i<res.size();i++) res2.add(res.get(i));
                res = res2;
            }
            System.out.println(1+" "+ans.size());
            for(int i=0;i<ans.size();i++)
            {
                int a[] = ans.get(i);
                System.out.println((a[0]+1)+" "+(a[1]+1)+" "+a[2]);
            }
            
        }
	}
	public static int[] f(List<Integer> res)
	{
	     int n = res.size();
	     int cnt = 0;
	     HashMap<Integer,Integer> map = new HashMap<>();
	     map.put(0,-1); int max = 0 ; int  l = -1; int r = -1; int b = -1;
	     for(int i=0;i<n;i++)
	     {
	         if(res.get(i)==0) cnt--;
	         else cnt++;
	         if(map.containsKey(cnt))
	         {
	           int ind = map.get(cnt);
	           if(i-ind>max) {
	               max = i-ind;
	               r = i;
	               l = ind+1;
	           }
	         }
	         else map.put(cnt,i);
	     }
	     if(cnt>=0) b = 0;
	     else b = 1;
	     return new int[]{l,r,b};
	}
}
