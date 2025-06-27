import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		FastScanner fs=new FastScanner();

		/******  CODE STARTS HERE  *****/
		//------------------------------------------------------------------------------------------------------------
		
		int t = fs.nextInt();
		while(t-->0) {
			int x=fs.nextInt(), y=fs.nextInt();
			//y = yellow * green
			boolean flag = false;
			
			if(y%2!=0) {
				System.out.println("NO");
				continue;
			}
			
			y/=2;
			
			int yellow=-1, green=-1;
			for(yellow = 1; yellow*yellow<=y; yellow++) {
				if(y%yellow != 0)continue;
				
				green = y/yellow;
				
				if(isPossible(yellow, green, x)) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				System.out.println("NO");
				continue;
			}
			
			System.out.println("YES");
			System.out.println(yellow+green);
			StringBuilder out = new StringBuilder();
			for(int i=2; i<green+2; i++) {
				out.append("1 "+ i + "\n");
			}
			for(int i=green+2; i<=yellow+green; i++) {
				out.append("2 "+ i + "\n");
			}
			System.out.print(out);
		}
	}
	
	static boolean isPossible(int yellow, int green, int x) {		
		return (yellow*(yellow-1)) + (green*(green-1)) + yellow + green ==  x; 
	}
    
	//******  CODE ENDS HERE  *****
	//----------------------------------------------------------------------------------------------------------------
	
	static void sort(int[] a) {
		ArrayList<Integer> l=new ArrayList<>();
		for (int i:a) l.add(i);
		Collections.sort(l);
		for (int i=0; i<a.length; i++) a[i]=l.get(i);
	}
	
	
	//----------- FastScanner class for faster input---------------------------
	static class FastScanner {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer("");
		String next() {
			while (!st.hasMoreTokens())
				try {
					st=new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		int[] readArray(int n) {
			int[] a=new int[n];
			for (int i=0; i<n; i++) a[i]=nextInt();
			return a;
		}
		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
