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
           String s = sc.next();
           int n = s.length();
           int even = 0; int odd= 0;
           int  cnt1=  0; int cnt0=  0;
          for(int i=0;i<n;i++)
          {
              if(s.charAt(i)=='1')
              {
                  cnt1++;
                  if(i%2==0) even++;
                  else odd++;
              }
              else cnt0++;
          }
            //  System.out.println(even+" "+odd);
          if(cnt1==1)
          {
             System.out.println(-1);
             continue;
          }
          int p = Math.min(even,odd);
          even-=p; odd-=p;
       
          if(even>0)
          {
             even%=3; 
             if(even==2)
             {
                int val1 = f1(s,n,0);
                if(val1!=-1)
                {
                    System.out.println(val1);
                    continue;
                }
                int val2 = f2(s,n,1);
                if(val2!=-1)
                {
                    System.out.println(val2);
                    continue;
                }
                if(cnt0>1)
                {
                    System.out.println(3);
                }
                else System.out.println(-1);
             }
             else if(even==1)
             {
                 int val1 = f1(s,n,1);
                 if(val1!=-1)
                 {
                     System.out.println(val1);
                     continue;
                 }
                 int val2 = f2(s,n,0);
                 if(val2!=-1)
                 {
                     System.out.println(val2);
                     continue;
                 }
                  if(cnt0>1)
                {
                    System.out.println(3);
                }
                else System.out.println(-1);
                 
             }
             else {
                 System.out.println(0);
             }
          }
          else if(odd>0)
          {
              odd%=3;
              if(odd==2)
              {
                  int val1 = f1(s,n,1);
                 if(val1!=-1)
                 {
                     System.out.println(val1);
                     continue;
                 }
                 int val2 = f2(s,n,0);
                 if(val2!=-1)
                 {
                     System.out.println(val2);
                     continue;
                 }
                  if(cnt0>1)
                {
                    System.out.println(3);
                }
                else System.out.println(-1); 
              }
              else if(odd==1)
              {
                int val1 = f1(s,n,0);
                if(val1!=-1)
                {
                    System.out.println(val1);
                    continue;
                }
                int val2 = f2(s,n,1);
                if(val2!=-1)
                {
                    System.out.println(val2);
                    continue;
                }
                if(cnt0>1)
                {
                    System.out.println(3);
                }
                else System.out.println(-1);
              }
              else {
                  System.out.println(0);
              }
          }
          else {
              System.out.println(0);
          }
       }
	}
	public static int f1(String s, int n, int t)
	{
	    for(int i=0;i<n;i++)
	    {
	        if(i%2==t&&s.charAt(i)=='1')
	        {
	            if(i-1>=0&&s.charAt(i-1)=='0') return 1;
	            if(i+1<n&&s.charAt(i+1)=='0') return 1;
	        }
	    }
	    return -1;
	}
	public static int f2(String s, int n,int t)
	{
	    int cnt = 0;
	     for(int i=0;i<n;i++)
	     {
	         if(i%2==t)
	         {
	             if(i>0&&s.charAt(i)=='1'&&s.charAt(i-1)=='0')
	             {
	                 cnt++;
	                 i++;
	             }
	         }
	         else {
	             if(i>0&&s.charAt(i)=='0'&&s.charAt(i-1)=='1')
	             {
	                 cnt++;
	                 i++;
	             }
	         }
	         if(cnt==2) return 2;
	     }
	     return -1;
	}
	
}
