/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
	  Scanner sc = new Scanner(System.in);
	  	
	  	int t = sc.nextInt();
	  	
	  	while(t-->0){
	  	int n = sc.nextInt();
		int[] a = new int[1 << n];
		for (int i = 0;i < a.length; i++) {
			a[i] = sc.nextInt();
		}
	  	
		Arrays.sort(a);
		while (n > 0) {
			int x = a[1];
			System.out.print(x + " ");
			int[] b = new int[1 << (n - 1)];
			Arrays.fill(b, -1);
			for (int i = 0, j1 = 0, j2 = 0; i < (1 << n); i++) {
				if (b[j2] != -1 && a[i] == b[j2] + x) {
					j2++;
				} else {
					b[j1++] = a[i];
				}
			}
			a = b;
			n--;
		}
	   	System.out.println();
	  		
	}
	}
}
