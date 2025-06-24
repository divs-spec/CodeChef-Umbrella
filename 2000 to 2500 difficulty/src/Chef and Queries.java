import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	static long[] buckets;
	public static void bucket_insert(long e) // Hash and insert element
	{
	    int ind = (int)(e % buckets.length);
	    while (buckets[ind] != 0L) // Linear probing
	    {
	        if (buckets[ind] == e)return; // When element already exists
	        ind = (ind != buckets.length-1) ? ind + 1 : 0;
	    }
	    buckets[ind] = e;
	}
	public static void bucket_remove(long e) // Hash and remove element
	{
	    int ind = (int)(e % buckets.length);
	    while (buckets[ind] != e) // Linear probing
	    {
	        if (buckets[ind] == 0L)return; // When element does not exist
	        ind = (ind != buckets.length-1) ? ind + 1 : 0;
	    }
	    buckets[ind] = 0;
	}
	
	public static void main (String[] args) throws java.lang.Exception
	{
	final int NBUCKET = 20000000; // Number of buckets of hash table
	final long MOD = ((long)Integer.MAX_VALUE+1)<<1;
	Scanner sc = new Scanner(System.in);
	//HashSet<Long> ss = new HashSet<>();
	// Prepare buckets
	buckets = new long[NBUCKET]; 
	Arrays.fill(buckets,0L);
	int Q = sc.nextInt();
	long S1 = sc.nextLong();
	long A = sc.nextLong();
	long B = sc.nextLong();
	long S = S1;
	long num = (S>>1);
	if (((S1&1) == 1)&&(num != 0)) {bucket_insert(num);} // Handle very first query
	for (int q = 1; q < Q; q++)
	{
	    S = (S*A+B)%MOD;
	    num = (S>>1);
	    if (((S&1)==1)&&(num != 0)) 
	    {
	        bucket_insert(num); //ss.add(num);
	    }
	    else if (num != 0)
	    {  
            bucket_remove(num); //ss.remove(num);
	    }
	}
	long res = Arrays.stream(buckets).sum() ; //0;
	System.out.println(res);
	}
}
