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
           int a[][] = new int[n][];
           HashMap<Integer,Integer> map = new HashMap<>();
           for(int i=0;i<n;i++)
           {
               int m = sc.nextInt();
               a[i] = new int[m];
               for(int j=0;j<m;j++)
               {
                   a[i][j] = sc.nextInt();
                   int k = Math.abs(a[i][j]);
                   map.put(k,map.getOrDefault(k,0)+1);
               }
           }
           long ans = 0L;
           for(int k : map.keySet())
           {
              int val = map.get(k);
              if(val>1) {
                  ans++;
              }
           }
           for(int i=0;i<n;i++)
           {
               int m = a[i].length;
                  int ind = m;
               for(int j=0;j<m;j++)
               {
                   if(a[i][j]>0) {
                       ind=  j;
                       break;
                   }
               }
                int l = ind-1; int r = ind;
                while(l>=0&&r<m)
                {
                    
                    if(Math.abs(a[i][l])<Math.abs(a[i][r]))
                    {
                        int val = map.get(Math.abs(a[i][l]));
                        if(val>1)
                        {
                          ans = ans+l;  
                        }
                        else {
                          ans = ans+(m-r);    
                        }
                        l--;
                    }
                    else {
                        int val = map.get(a[i][r]);
                        if(val>1)
                        {
                            ans = ans+(m-r-1);
                        }
                        else {
                            ans=  ans+(l+1);
                        }
                        r++;
                    }
                }
                while(l>=0)
                {
                   int val = map.get(Math.abs(a[i][l]));
                        if(val>1)
                        {
                          ans = ans+l;  
                        }
                        l--;
                }
                while(r<m)
                {
                   int val = map.get(a[i][r]);
                        if(val>1)
                        {
                            ans = ans+(m-r-1);
                        }
                        r++;
                }
           }
           System.out.println(ans);
       }
	}
}
