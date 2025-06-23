import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while(test-->0)
        {
            int n = sc.nextInt();
            int q = sc.nextInt();
            String s = sc.next();
            String t = sc.next();
            long a[] = new long[n];
           
            for(int i=0;i<n;i++)
            {
                int ind1 = s.charAt(i)-'a';
                int ind2 = t.charAt(i)-'a';
                int op = (ind2>=ind1)?(ind2-ind1):(ind2+26-ind1);
                if(i>0) a[i] = a[i-1];
                if(i%2==0) a[i] = (a[i]+(long)op)%26;
                else a[i] = (a[i]-(long)op+26)%26;
               
            }
            for(int i=0;i<q;i++)
            {
                int l = sc.nextInt()-1;
                int r = sc.nextInt()-1;
                long val = a[r]-(l-1>=0?a[l-1]:0);
                if(val==0)
                {
                    System.out.println("Yes");
                }
                else {
                    System.out.println("No");
                }
            }
        }
	}
}
