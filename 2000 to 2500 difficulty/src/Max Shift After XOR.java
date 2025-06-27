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
            long a[] = new long[n];
            for(int i=0;i<n;i++) a[i] = sc.nextLong();
            long xor = 0;
            long pre[] = new long[n];
            HashMap<Long,Integer> map = new HashMap<>();
            for(int i=0;i<n;i++)
            {
                xor = xor^a[i];
                pre[i] = xor;
                map.put(pre[i],map.getOrDefault(pre[i],0)+1);
            }
            xor = 0;
            int ans = map.size();
            for(int i=n-1;i>=1;i--)
            {
                map.put(pre[i],map.getOrDefault(pre[i],0)-1);
                if(map.get(pre[i])==0) map.remove(pre[i]);
                map.put(xor,map.getOrDefault(xor,0)+1);
                xor = xor^a[i];
                ans = Math.max(ans,map.size());
            }
            System.out.println(ans);
        }
	}
}

/*
EXPLANATION OF THE CODE :

This Java code solves a problem involving prefix XORs and finding the maximum number of distinct XOR values by splitting the array at different points.

🔍 Problem Idea:
You're given an array a[]. You want to:

Split the array into two parts at some point.

In each part, compute prefix XORs (cumulative XOR from the start).

The goal is to find the split that gives the maximum number of distinct prefix XORs combined from both parts.

🧩 How it works:
Read input n and array a[].

Build a prefix XOR array pre[]:

pre[i] = a[0] ^ a[1] ^ ... ^ a[i]

Also, count how many times each XOR value occurs using a map.

Start from the end of the array:

At each step, remove pre[i] from the map (removing right side prefix).

Add the XOR of the right side (xor) to the map.

Update xor = xor ^ a[i] (building XOR from right to left).

Track the maximum size of the map (i.e., number of unique prefix XORs).

✅ Final Result:
The answer is the maximum number of distinct XORs that can be obtained by splitting the array into two parts.

It uses prefix XOR and a HashMap to track frequencies efficiently.

*/
