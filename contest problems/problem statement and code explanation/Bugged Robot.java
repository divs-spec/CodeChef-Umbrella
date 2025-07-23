✅ Intuition:
We want to split a number n into two factors d1 and d2 such that their sizes (in terms of binary bit-length) are balanced. The total number of bits used to represent both numbers should be the smallest possible (L), where one gets about L/2 bits and the other gets the rest.

✅ Approach:
For each test case, read n.

Find all pairs (d1, d2) such that d1 * d2 = n.

For values of L from 1 to 65:

Split L into two parts: even and odd.

For each factor d1 of n, check if:

d1 fits in even bits → i.e., lies in [even+1, 2^even]

d2 = n/d1 fits in odd bits → lies in [odd+1, 2^odd]

If both fit, that L is valid; break and print it.

✅ Code Explanation:
getrange(k) returns the range of numbers that fit in exactly k bits: [k+1, 2^k].

We loop through all divisors of n, and for each L from 1 to 65, check if a valid (d1, d2) pair exists within the allowed ranges.

The first valid L found is the answer.
