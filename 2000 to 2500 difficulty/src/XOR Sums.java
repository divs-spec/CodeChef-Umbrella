/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	static final int mod = 998244353; // Number where mod is taken
	static final int modmo = mod-1; // And this number minus one
	public static long mexp(long num, int e) // Modular exponentiation
	{
	    long a = num;
	    long res = 1L;
	    while (e != 0)
	    {
	        if ((e&1)==1) res = (res*a)%mod;
	        a = (a*a)%mod;
	        e = (e>>1);
	    }
	    return res;
	}
	public static long[] fft(int len, long[] arr, long alp) // Fast Fourier transform
	{
	    if (len == 1) return arr; // Base case 
	    final int lhalf = (len>>1);
	    long[] arr_o = new long[lhalf]; // Odd part
	    long[] arr_e = new long[lhalf]; // Even part
	    for (int i = 0; i < len; i+=2) // Subdivide into even and odd parts
	    {
	        arr_e[(i>>1)] = arr[i];
	        arr_o[(i>>1)] = arr[i+1];
	    }
	    long alp2 = (alp*alp)%mod; // Double the exponent
	    long[] ev = fft(lhalf,arr_e,alp2);
	    long[] odd = fft(lhalf,arr_o,alp2);
	    long[] ret = new long[len]; // Return array 
	    long palp = 1L; // Power of alpha
	    for (int i = 0; i < lhalf; i++)
	    {
	        //if ((i == 1)&&(len > 2))System.err.println(palp);
	        ret[i] = (ev[i]+palp*odd[i])%mod;
	        ret[i+lhalf] = (ev[i]+modmo*((palp*odd[i])%mod))%mod;
	        palp = (palp*alp)%mod; // Update power of alpha
	    }
	    return ret;
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		int base = 2; // Base of the primitive root
		while (true)
		{
		    if ((mexp(base,modmo/2) != 1)&&(mexp(base,modmo/7) != 1)&&(mexp(base,modmo/17) != 1)) break; // When suitable base is found
		    base++;
		}
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int exponent = (int)(Math.log(N)/Math.log(2))+1; // Suitable power of two 
		//System.err.println(mexp(base,modmo/2));
		final int sz = (1<<exponent); // Size of the FFT signals
		long alpha = mexp(base,modmo/sz); // Primitive root
		long alpha_inv = mexp(alpha,sz-1); // Inverse of the primitive root 
		//System.err.println((alpha_inv*alpha)%mod);
		final long inv2 = mexp(2L,mod-2); // Inverse of two 
		final long invsz = mexp(sz,mod-2); // Inverse of the FFT signal size
	    int[] A = new int[N];
		for (int i = 0; i < N; i++) // Array input
        {
            A[i] = sc.nextInt();
		}
		long[] pol = new long[sz]; // Generating polynomials
		Arrays.fill(pol,0);
		long pt = 1L; // Power of two according to bit position
		for (int pos = 0; pos < 31; pos++) // Iterate over bits 
	    {
	        //for (int k = 0; k < sz; k++) pol[k] = ((pol[k]<<1))%mod; // Shift bits in generating polynomial
	        int nsb = 0; // Find number of set bits in array at this position 
	        for (int i = 0; i < N; i++)
	        {
	            //if (i == 2)System.err.println((A[i]&1));
	            nsb += (A[i] & 1);
	            A[i] = (A[i] >> 1);
	        }
	        long pa = 1L; // Power of alpha
	        for (int k = 0; k < sz; k++) // Make generating polynomial for this bit
	        {
	            long dpol = mexp(1L-pa+mod,nsb); // For set bits switch parity (factor -1)
	            dpol = (dpol*mexp(1L+pa,N-nsb))%mod; // Handle all other bits that leave parity invariant
	            dpol = (inv2*(dpol-mexp(1L+pa,N)+mod))%mod; // Make a shift
	            dpol = (modmo*dpol)%mod; // Change sign
	            pol[k] = (pol[k]+pt*dpol)%mod; // Add to result
	            pa = (pa*alpha)%mod; // Update power of alpha
	        }
	        pt = (pt<<1); // Change power of two
	    }
	    //System.err.println(Arrays.toString(pol));
	    long[] part_sums = fft(sz,pol,alpha_inv); // Get partial sums 
	    //System.err.println(Arrays.toString(part_sums));
	    int res[] = new int[N]; // Result array
	    long ps = 0L; // Prefix sum
	    for (int i = 0; i < N; i++)
	    {
	        ps = (ps+part_sums[i+1]*invsz)%mod;// Accumulate the prefix sum
	        res[i] = (int)ps;
	    }
	    // Last part: Query handling
	    int Q = sc.nextInt();
	    while (Q != 0)
	    {
	        int M = sc.nextInt();
	        System.out.println(res[M-1]);
	        Q--;
	    }
	}
}

/*
EXPLAINATION OF CODE:
This Java program uses Fast Fourier Transform (FFT) to efficiently compute answers to a bitwise XOR-related problem over an array of integers. Let's break it down simply:

🧩 Objective:
Given an array A of N integers and Q queries, the program computes results involving bitwise XOR sums of all subarrays in a highly optimized way using FFT and generating polynomials.

🛠️ How it Works:
Bit Processing (Loop over 31 bits):
Each number is processed bit by bit (up to 31 bits for a 32-bit integer). For each bit:

It counts how many numbers have this bit set (nsb).

Constructs a generating function (polynomial) representing XOR behavior of this bit across subarrays.

It accumulates this into a polynomial pol, weighted by bit position (like 1 << bit).

FFT for Efficient Computation:

Uses a recursive FFT method to transform the polynomial (fft()).

Computes partial sums efficiently using modulo arithmetic, handling cyclic convolution to combine bit contributions.

Query Processing:

After preprocessing, the result for each prefix [0...M-1] is precomputed and stored in res[].

For each query, it directly outputs res[M-1].

⚡ Performance:
This approach is much faster than brute force (which would take O(N²)), making it suitable for large inputs (N up to 10⁵+). It uses advanced number-theoretic transforms and modular math efficiently.

✅ In Short:
This is an advanced bitwise + polynomial-based solution using FFT for fast subarray XOR handling, suitable for competitive programming.
*/
