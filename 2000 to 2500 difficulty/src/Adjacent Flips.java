import java.io.*;
import java.util.*;
class ADJFLIP {
	static class FastReader {
		BufferedReader br; StringTokenizer st;
		public FastReader(){
			br=new BufferedReader(new InputStreamReader(System.in));}
		String next(){
			while(st==null || !st.hasMoreElements())
				try{ st=new StringTokenizer(br.readLine()); }
				catch(IOException e){ e.printStackTrace(); }
			return st.nextToken(); }
		int nextInt(){ return Integer.parseInt(next()); }
	}
	public static void main(String[] args) {
		FastReader in=new FastReader();
		PrintWriter out=new PrintWriter(System.out);
		int tt=in.nextInt();
		while(tt-->0) {
			in.nextInt();
			String s=in.next();
			StringBuilder t=new StringBuilder();
			t.append(s);
			for(int i=0;i<t.length()-1;i++)
				if(t.charAt(i)==t.charAt(i+1)) {
					t.delete(i, i+2);
					i=Math.max(-1, i-2);
				}
			out.println(t.length()<2?"Yes":"No");
		} out.close();
	}
}
