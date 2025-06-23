

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
          int res[] = new int[n+1];
          int flag = 0;
          for(int i=0;i<n;i++)
          {
              a[i] = sc.nextInt();
              if(a[i]==-1) continue;
              if(a[i]>i+1)
              {
                  flag = 1;
              }
              else res[a[i]] = 1;
          }
          if(!check(a,n)||flag==1)
          {
              System.out.println(-1);
              continue;
          }
        //   System.out.println("YES");
          List<Integer> ans = new ArrayList<>();
          int mex[] = new int[n+1];
          int m = 0;
          int ind = 0;
          int cur = -1; int i = 0;
          while(i<n)
          {
              int j = i;
              while(j<n&&(a[j]==cur||a[j]==-1)) j++;
              int cnt = cur==-1?j-i+1:j-i;
              while(cnt>0)
              {
                  if(ind>n)
                  {
                    ans.add(ind);
                  }
                  if(res[ind]==0) {
                      mex[ind] = 1;
                      ans.add(ind++);
                      cnt--;
                  }
                  else {
                     ind++; 
                  }
              }
              if(cur!=-1)
              {
                ans.add(cur);
                mex[cur] = 1;  
              }
              while(m<n&&mex[m]==1) m++;
              if(j<n&&m!=a[j]) {
                  flag = 1;
                  break;
              }
              cur = m;
              i = j+1;
          }
          if(flag==1)
          {
              System.out.println(-1);
              continue;
          }
          for(i=0;i<n;i++)
          {
              System.out.print(ans.get(i)+" ");
          }
          System.out.println();
      }
	}
	public static boolean check(int a[],int n)
	{
	    int prev = -1;
	    for(int i=0;i<n;i++)
	    {
	        if(a[i]==-1) continue;
	        if(a[i]<prev) return false;
	        prev = a[i];
	    }
	    return true;
	}
}
