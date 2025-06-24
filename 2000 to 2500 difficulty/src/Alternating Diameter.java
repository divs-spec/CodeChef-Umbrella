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
            int b = sc.nextInt();
            int w = sc.nextInt();
            int n = b+w;
            if(n==1)
            {
                System.out.println(b==0?'W':'B');
                continue;
            }
            if(w==0||b==0)
            {
                System.out.println(-1);
                continue;
            }
            if(n==2) {
                System.out.println("WB");
                System.out.println(1+" "+2);
                continue;
            }
            char ch[] = new char[n];
            if(w>1)
            {
                ch[1] = 'W'; ch[0] = 'B'; ch[2] = 'W';
                w-=2;
                b--;
            }
            else {
                ch[1] = 'B'; ch[0] = 'W'; ch[2] = 'B';
                w--;
                b-=2;
            }
            for(int i=3;i<n;i++)
            {
                if(w>0) {
                    ch[i] = 'W';
                    w--;
                }
                else {
                    ch[i] = 'B';
                }
            }
            System.out.println(new String(ch));
            for(int i=2;i<=n;i++)
            {
                System.out.println(1+" "+i);
            }
        }
	}
}
