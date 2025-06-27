import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int spf[];
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
       if(spf==null)
       {
           spf = new int[1000001];
           for(int i=0;i<=1000000;i++)
           {
               spf[i] = i;
           }
           for(int i=2;i<=1000000;i++)
           {
              if(spf[i]==i)
              {
               for(int j=i;j<=1000000;j+=i)
               {
                  if(spf[j]==j) {
                      spf[j] = i;
                  } 
               }
              }
           }
       }
       Scanner sc   = new Scanner(System.in);
       int test = sc.nextInt();
       while(test-->0)
       {
           int n = sc.nextInt();
           int a[] = new int[n];
           HashMap<Integer,PriorityQueue<Integer>> map = new HashMap<>();
           PriorityQueue<Integer> pq = new PriorityQueue<>((i1,i2)->Integer.compare(a[i1],a[i2]));
           for(int i=0;i<n;i++)
           {
               int k = sc.nextInt();
               a[i] = k;
               pq.offer(i);
               while(k>1)
               {
                   int d = spf[k];
                   while(k>1&&k%d==0)
                   {
                       k = k/d;
                   }
                   if(!map.containsKey(d))
                   {
                       map.put(d,new PriorityQueue<>((i1,i2)->Integer.compare(a[i1],a[i2])));
                   }
                   map.get(d).offer(i);
               }
           }
           int vis[] = new int[n];
           int q = sc.nextInt();
           for(int i=0;i<q;i++)
           {
               int x = sc.nextInt();
               int ind = -1;
               while(x>1)
               {
                   int d = spf[x];
                   while(x>1&&x%d==0)
                   {
                     x = x/d;  
                   }
                   if(map.containsKey(d))
                   {
                       while(!map.get(d).isEmpty()&&vis[map.get(d).peek()]==1) map.get(d).poll();
                       if(!map.get(d).isEmpty())
                       {
                           if(ind==-1||a[ind]>a[map.get(d).peek()])
                           {
                               ind = map.get(d).peek();
                           }
                       }
                   }
               }
               if(ind==-1)
               {
                   while(!pq.isEmpty()&&vis[pq.peek()]==1) pq.poll();
                   ind = pq.peek();
               }
               vis[ind] = 1;
               System.out.print(a[ind]+" ");
           }
           System.out.println();
       }
	}
}

/*

Explanation of the code : 

This Java code efficiently solves a selection problem involving prime factor optimization. It picks the best number from a list based on the prime factors of a query number.

🎯 Goal:
You’re given:

A list of numbers a[]

Several queries with numbers x

For each query, you must:

Pick the smallest number from a[] that shares any prime factor with x, and hasn't been picked before.

If no such number exists, pick the smallest unused number from a[].

🧠 How It Works:
1. Preprocessing with SPF (Smallest Prime Factor):
Builds an array spf[] to store the smallest prime factor for every number up to 1,000,000 using a modified sieve of Eratosthenes.

This allows fast prime factorization of any number in O(log n) time.

2. Input and Setup:
Reads n numbers into array a[].

Creates:

A global min-heap pq to track unused smallest elements.

A map from prime p → min-heap of indices of numbers in a[] that have p as a prime factor.

3. Processing Queries:
For each query number x:

Get all its prime factors using spf[].

For each factor d, check the heap map.get(d) and pick the smallest unused number that has d as a prime factor.

If none found, fall back to pq (which contains all unused numbers).

Mark the chosen number as used using vis[] and output its value.

✅ Key Features:
Efficient factorization using spf[].

Fast retrieval using priority queues (min-heaps).

Avoids duplicates using vis[] to mark used numbers.

📌 Summary:
This code cleverly uses number theory and heaps to handle multiple queries efficiently, always returning the best available number based on shared prime factors.

*/
