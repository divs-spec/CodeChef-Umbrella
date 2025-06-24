import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc = new Scanner (System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int d = sc.nextInt();
            List<Integer> res = new ArrayList<>();
            res.add(2);
            int next = 3;
            int add = 1;
            while(d>0)
            {
                if(d>=add)
                {
                    res.add(next);
                    d-=add;
                    next = next==2?3:2;
                    add++;
                }
                else {
                    res.add(1);
                    res.add(2);
                    add = 1;
                    next = 3;
                }
            }
            System.out.println(res.size());
            for(int i=0;i<res.size();i++)
            {
                System.out.print(res.get(i)+" ");
            }
            System.out.println();
        }
	}
}
