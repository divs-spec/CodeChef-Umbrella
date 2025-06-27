import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static long mod = 998244353;
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0)
		{
		    long a  = sc.nextLong();
		    long b  = sc.nextLong();
		    long n = a+b;
		  //  long gcd = gcd(a,n);
		  //  System.out.println(gcd);
		  //  a = a/gcd; n = n/gcd;
		    long val = n/2+n%2l;
		    long ans = (a*power(n,mod-2l))%mod;
		    System.out.println((ans*val)%mod);
		}

	}
	public static long gcd(long a, long b)
	{
	    return a==0?b:gcd(b%a,a);
	}
	public static long power(long a, long n)
	{
	    long res = 1;
	    while(n>0)
	    {
	        if(n%2==0)
	        {
	           a = (a*a)%mod; n = n/2l;
	        }
	        else {
	            res = (res*a)%mod; n--;
	        }
	    }
	    return res;
	}
}

/*

This Java program solves a mathematical problem for multiple test cases using modular arithmetic. 
For each test case, it reads two numbers a and b, and calculates their sum n = a + b. 
The goal is to compute the following expression:

(𝑎 / 𝑎+𝑏) × (⌈𝑎+𝑏 / 2⌉) mod 998244353

Here's a simple breakdown of what the code does:

Read Input: It reads a and b for each test case.

Compute n: Adds them to get n = a + b.

Calculate Ceiling(n/2): This is done using val = n / 2 + n % 2.

Modular Inverse: Since division in modular arithmetic isn't straightforward, it finds the modular inverse of n using Fermat’s Little Theorem, which works because 998244353 is a prime number.

Final Answer: It computes (a * inverse(n) % mod) * val % mod — this gives the final result, which is printed.

This formula is useful in problems involving probabilities or expectations under modulo. 
It avoids actual division by replacing it with multiplication using modular inverse. 
The code is optimized and uses fast exponentiation for large values.

*/
