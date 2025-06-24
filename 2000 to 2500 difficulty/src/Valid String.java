import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int q = sc.nextInt();
       while(q-->0)
       {
           int n = sc.nextInt();
           int c = sc.nextInt();
           String s = sc.next();
           int p = -1; int t = -1; int flag = 0; int val = 0; int one = -1;
           for(int i=0;i<n;i++)
           {
               if(s.charAt(i)=='1')
               {
                   if(p==-1) val = i;
                   if(i-p-1>c&&p!=-1)
                   {
                       if(t>0) {
                           flag  =1;
                           break;
                       }
                       t = i;
                   }
                   p = i;

               }
           }
           if(flag==1||(t>0&&val+n-1-p>c))
           {
               System.out.println("NO");
           }
           else {
               System.out.println("YES");
           }
       }
	}
}
