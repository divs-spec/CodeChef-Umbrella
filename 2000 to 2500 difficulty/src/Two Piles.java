/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	static int piles[][] = new int[400004][2];
    static int piles_copy[][] = new int[400004][2];
    static boolean frequency[];
	public static void main (String[] args) throws java.lang.Exception
	{
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		while(t-- > 0) {
			frequency = new boolean[200002];
		    int n = input.nextInt();
		    for (int i=0; i < n; i++) {
		        piles[2*i][0] = input.nextInt();
		        piles[2*i][1] = i;
		        piles[2*i+1][0] = input.nextInt();
		        piles[2*i+1][1] = i;
		    }
		    parting(0, 2*n-1);
		    int minimum = 1000000003;
			for (int i=0; i < 2*n-1; i++) {
			    if (piles[i][1] != piles[i+1][1] && piles[i][0] - piles[i+1][0] < minimum) {
			        minimum = piles[i][0] - piles[i+1][0];
			    } else if (piles[i][1] == piles[i+1][1] && i < 2*n-2 && piles[i][0] - piles[i+2][0] < minimum) {
			        minimum = piles[i][0] - piles[i+2][0];
			    }
			    if (frequency[piles[i][1]]) {
			    	break;
			    }
			    frequency[piles[i][1]] = true;
			}
			System.out.println(minimum);
		}
	}
	
	private static void parting(int low, int high) {
	    if (low < high) {
	        int mid = (low+high)/2;
	        parting(low, mid);
	        parting(mid+1, high);
	        merge(low, mid, high);
	    }
	}
	
	private static void merge(int low, int mid, int high) {
	    int i = low, j = mid+1, k = low;
	    while(i < mid+1 && j < high+1) {
	        if (piles[i][0] <= piles[j][0]) {
	            piles_copy[k][0] = piles[j][0];
	            piles_copy[k++][1] = piles[j++][1];
	        } else {
	            piles_copy[k][0] = piles[i][0];
	            piles_copy[k++][1] = piles[i++][1];
	        }
	    }
	    while(i < mid+1) {
	       piles_copy[k][0] = piles[i][0];
	       piles_copy[k++][1] = piles[i++][1];
	    }
	    while(j < high+1) {
	       piles_copy[k][0] = piles[j][0];
	       piles_copy[k++][1] = piles[j++][1];
	    }
	    
	    for(i=low; i<=high; i++) {
	        piles[i][0] = piles_copy[i][0];
	        piles[i][1] = piles_copy[i][1];
	    }
	}
}
