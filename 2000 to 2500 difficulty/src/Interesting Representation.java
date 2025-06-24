import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int prime[] = {
        3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97
    };
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       Scanner sc = new Scanner(System.in);
       int t = sc.nextInt();
       while(t-->0)
       {
           long n = sc.nextLong();
           if(n%2==0)
           {
               System.out.println(2L*n+" "+n);
               continue;
           }
           for(int i=0;i<prime.length;i++)
           {
               int val = prime[i];
               if(n%val==0) continue;
               System.out.println((n*val)+" "+(n*(val-1)));
               break;
           }
       }
	}
}
