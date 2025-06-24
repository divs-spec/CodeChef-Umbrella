import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
        Scanner sc  = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        HashMap<String,Integer> map = new HashMap<>();
        map.put("0$0$0",1);
        int cnta = 0; int cntb = 0; int cntc = 0;
        long ans=  0L;
        for(int i=0;i<n;i++)
        {
            if(s.charAt(i)=='A') cnta++;
            else if(s.charAt(i)=='B') cntb++;
            else cntc++;
            int min = Math.min(cnta,Math.min(cntb,cntc));
            int  a = cnta-min; int b = cntb-min; int c = cntc-min; 
            String key = String.valueOf(a)+"$"+String.valueOf(b)+"$"+String.valueOf(c);
            int val = map.getOrDefault(key,0);
            ans+=val;
            map.put(key,val+1);
        }
        System.out.println(ans);
	}
}
