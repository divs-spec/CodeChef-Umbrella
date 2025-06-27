import java.util.*;
import java.io.*;
// Never Give Up
public class Main{
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while(st==null || !st.hasMoreTokens()){
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str="";
            try {
                str=br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static class FastWriter {
		private final BufferedWriter bw;

		public FastWriter() {
			this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void print(Object object) throws IOException {
			bw.append("" + object);
		}

		public void println(Object object) throws IOException {
			print(object);
			bw.append("\n");
		}

		public void close() throws IOException {
			bw.close();
		}
	}
    public static void main(String[] args) {
        try {
            FastReader sc=new FastReader();
            FastWriter out = new FastWriter();
            int testCases=sc.nextInt();
            while(testCases-- > 0){
                // Never Give Up be Consistent
                int n=sc.nextInt();
                int[] a=new int[n];
                int count=0;
                for(int i=0;i<n;i++)
                a[i]=sc.nextInt();
                int[] countArray=getCountArray(a);
                // printArray(countArray);
                int index1=getMax(countArray);
                int index2=getFirstOne(countArray,index1);
                if(countArray[index1]==0)
                System.out.println(0);
                else
                {
                    // System.out.println(countArray[index]+" "+index);
                    int[] res=getResultantArray(countArray,index1,index2);
                    System.out.println(res.length);
                    printArray(res);
                }
            }
            out.close();
        }
        catch (Exception e) {
            System.out.println(e);
            return;
        }
    }
    public static int getFirstOne(int[] countArray,int index){
        while(index>=0&&countArray[index]!=0)index--;
        return index+1;
    }
    public static void printArray(int[] a){
        StringBuilder sb=new StringBuilder();
        for(int i:a)
        sb.append(i+" ");
        System.out.println(sb);
    }
    public static int[] getResultantArray(int[] a,int index1,int index2){
        int[] res=new int[a[index1]+1];
        res[0]=index2+1;
        int i=1;
        int max=0;
        while(index2<=index1){
            int num=a[index2];
            if(num>max){
                max=num;
                res[i++]=index2+2;
            }
            index2++;
        }
        return res;
    }
    public static int[] getCountArray(int[] a){
        int n=a.length;
        int[] res=new int[n];
        int count=0;
        for(int i=0;i<n;i++)
        {
            if(a[i]==0){
                if(count==0)continue;
                else count--;
            }
            else{
                count++;
            }
            res[i]=count;
        }
        return res;
    }
    public static int getMax(int[] a){
        int max=-1;
        int index=-1;
        int n=a.length;
        for(int i=0;i<n;i++)
        {
            if(a[i]>max){
                max=a[i];
                index=i;
            }
        }
        return index;
    }
}

/*
Explanation of code : 

This Java code solves a bracket balancing problem using an integer array instead of actual brackets.

🧠 Problem Setup:
You're given an array a[] of size n, with elements either 0 or 1.

Think of 1 as an opening bracket (.

Think of 0 as a closing bracket ).

🎯 Goal:
Find the longest valid bracket sequence (a balanced sequence of ( and )), and print:

The number of valid brackets + 1

The starting positions (1-based) where you can see the sequence building up

🔍 How it Works:
getCountArray(a):

Builds a prefix balance array:

Adds 1 for 1, subtracts 1 for 0 (if possible), and stores the current "balance".

getMax():

Finds the index with the maximum balance (deepest nesting).

getFirstOne():

Finds where that nesting started (first time balance > 0).

getResultantArray():

Constructs the result:

First element: starting index

Then adds indices where the balance increases.

✅ Output:
First, the number of items in the result.

Then, the indices showing how the balanced sequence is built.

This is a smart way to analyze bracket structures using only numbers!

*/
