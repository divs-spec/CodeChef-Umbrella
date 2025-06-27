import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    static int MAX = 10000000;
    static int[] spf = new int[MAX + 1]; // smallest prime factor

    // Precompute smallest prime factors for all numbers up to MAX
    static void sieve() {
        spf[1] = 1;
        for (int i = 2; i <= MAX; i++) {
            spf[i] = 0;
        }
        for (int i = 2; i * i <= MAX; i++) {
            if (spf[i] == 0) {
                for (int j = i * i; j <= MAX; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        for (int i = 2; i <= MAX; i++) {
            if (spf[i] == 0) spf[i] = i;
        }
    }

    // Factorize number and return a map of prime -> exponent parity (0 or 1)
    static Map<Integer, Integer> factorize(int x) {
        Map<Integer, Integer> primeCount = new HashMap<>();
        while (x > 1) {
            int p = spf[x];
            primeCount.put(p, primeCount.getOrDefault(p, 0) ^ 1);
            x /= p;
        }
        return primeCount;
    }

    // Convert factor parity map to a canonical string key
    static String getKey(Map<Integer, Integer> factorParity) {
        List<Integer> primes = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : factorParity.entrySet()) {
            if (entry.getValue() == 1) {
                primes.add(entry.getKey());
            }
        }
        Collections.sort(primes);
        StringBuilder sb = new StringBuilder();
        for (int p : primes) {
            sb.append(p).append(",");
        }
        return sb.toString();
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sieve();

        int T = Integer.parseInt(br.readLine());
        StringBuilder output = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] parts = br.readLine().split(" ");
            Map<String, Integer> freq = new HashMap<>();

            for (int i = 0; i < N; i++) {
                int val = Integer.parseInt(parts[i]);
                Map<Integer, Integer> factorParity = factorize(val);
                String key = getKey(factorParity);
                freq.put(key, freq.getOrDefault(key, 0) + 1);
            }

            int maxLen = 0;
            for (Map.Entry<String, Integer> entry : freq.entrySet()) {
                String key = entry.getKey();
                int count = entry.getValue();
                if (!key.isEmpty()) {
                    if (count > maxLen) {
                        maxLen = count;
                    }
                }
            }

            output.append(maxLen).append("\n");
        }

        System.out.print(output);
    }
}

/*
Explanation of the code : 

This Java program solves a problem involving square-free products and subarrays using number theory (prime factorization) and bitmasking-style logic.

🧠 Problem Overview:
For each test case:

You're given N integers.

You must find the maximum number of elements in the array such that the product of any chosen subarray is not a perfect square.

🔍 How the Code Works:
1. Smallest Prime Factor (SPF) Preprocessing:
The sieve() function precomputes the smallest prime factor for every number up to MAX = 10^7.

It’s like a modified Sieve of Eratosthenes.

Helps quickly factorize any number.

2. Prime Factorization with Parity:
Each number is factorized, and only odd powers of primes are recorded.

Why? Because a perfect square has even powers of all primes.

So we identify numbers by the set of primes with odd exponents → these define the "square-free" part.

3. Key Generation:
After factorizing, a canonical string key is built using primes with odd exponents (sorted).

Example:

12 = 2^2 * 3^1 → key = "3,"

18 = 2^1 * 3^2 → key = "2,"

36 = 2^2 * 3^2 → key = "" (perfect square)

4. Counting Frequency:
The frequency of each unique key is stored.

Among non-empty keys (non-square-perfect forms), we find the maximum frequency.

5. Result:
For each test case, print the maximum group size where all values share the same "square-free signature".

✅ Summary:
The program efficiently groups numbers by their square-free core and finds the largest such group — avoiding perfect squares. It's a classic use of prime factorization + hashing + map grouping.

*/
