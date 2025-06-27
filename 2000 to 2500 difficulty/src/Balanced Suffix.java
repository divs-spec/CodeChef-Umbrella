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
            int k = sc.nextInt();
            String s = sc.next();
            int cnt[] = new int[26];
            for(int i=0;i<n;i++)
            {
                int ind = s.charAt(i)-'a';
                cnt[ind]++;
            }
            int max = 0; int min = n+1;
            HashSet<Integer> set = new HashSet<>();
            for(int i=0;i<26;i++)
            {
                if(cnt[i]>0)
                {
                min = Math.min(min,cnt[i]);
                max = Math.max(max,cnt[i]);
                set.add(i);
                }
            }
            if(max-min>k)
            {
                System.out.println(-1);
                continue;
            }
            StringBuilder ans = new StringBuilder();
            for(int i=0;i<26;i++)
            {
                if(cnt[i]==0)
                {
                    cnt[i] = -1;
                }
            }
            while(ans.length()<n)
            {
                int pmin[] = new int[26]; Arrays.fill(pmin,Integer.MAX_VALUE);
                int pmax[] =  new int[26];
                int smin[] = new int[26];
                int smax[] = new int[26]; Arrays.fill(smin,Integer.MAX_VALUE);
                for(int i=0;i<26;i++)
                {
                    if(i-1>=0)
                    {
                        pmin[i] = pmin[i-1];
                        pmax[i] = pmax[i-1];
                    }
                    if(cnt[i]!=-1)
                    {
                        pmin[i] = Math.min(pmin[i],cnt[i]);
                        pmax[i] = Math.max(pmax[i],cnt[i]);
                    }
                }
                for(int i=25;i>=0;i--)
                {
                    if(i+1<26)
                    {
                        smin[i] = smin[i+1];
                        smax[i] = smax[i+1];
                    }
                    if(cnt[i]!=-1)
                    {
                        smin[i] = Math.min(smin[i],cnt[i]);
                        smax[i] = Math.max(smax[i],cnt[i]);
                    }
                }
                for(int i=0;i<26;i++)
                {
                   if(!set.contains(i)||cnt[i]==0) continue;
                   int m1 = Math.min(i-1>=0?pmin[i-1]:Integer.MAX_VALUE,i+1<26?smin[i+1]:Integer.MAX_VALUE);
                   int m2 = Math.max(i-1>=0?pmax[i-1]:0,i+1<26?smax[i+1]:0);
                   int val = cnt[i]-1;
                   m1 = Math.min(m1,val);
                   m2 = Math.max(m2,val);
                //   System.out.println(m1+" "+m2+" "+i);
                   if(m2-m1<=k)
                   {
                       cnt[i]--;
                       char ch = (char)('a'+i);
                       ans.append(ch);
                       break;
                   }
                }
            }
            System.out.println(ans);
        }
	}
}

/*

Explanation of code :

This Java code solves a string rearrangement problem where we want to construct a new string from a given one, under a balancing constraint.

🧠 Problem Overview:
You're given a string s of length n and an integer k.

You can rearrange the characters, but the goal is to form a new string such that:

The difference between the most and least frequent characters used at any point during construction never exceeds k.

🧩 How It Works:
Count Characters:

The code first counts how many times each letter ('a' to 'z') appears in the original string.

Initial Check:

If the difference between max and min frequency of characters exceeds k, it's impossible to form such a balanced string — print -1.

Greedy Construction:

The new string is built one character at a time.

For each possible character, it checks:

If removing one instance still keeps the remaining character frequencies within the k difference.

It uses prefix and suffix arrays to track min/max frequencies efficiently.

Selection:

The character that keeps the distribution valid is chosen and added to the result.

This is repeated until the entire string is rebuilt.

✅ Result:
The final output is a rearranged version of s that maintains frequency balance at every step. It’s a smart use of greedy logic + frequency analysis.

*/
