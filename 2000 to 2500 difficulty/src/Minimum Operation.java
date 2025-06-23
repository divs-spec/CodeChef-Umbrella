
import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;
public class Main{
    public static void main(String args[]) throws IOException{
        Read sc=new Read();
        int n=sc.nextInt();
        boolean prime[]=new boolean[1000005];
        Arrays.fill(prime,true);
        for(int j=2;j<=1000000;j++){
            if(prime[j]){
                for(int i=2;i*j<=1000000;i++){
                    prime[i*j]=false;
                }
            }
        }
        for(int i=0;i<n;i++){
            int m=sc.nextInt();
            int x=sc.nextInt();
            int arr[]=new int[m];
            for(int j=0;j<m;j++){
                arr[j]=sc.nextInt();
            }
            Arrays.sort(arr);
            if(arr[0]==arr[m-1]){
                sc.println(0);
            }
            else{
                int g=arr[0];
                for(int a:arr){
                    g=gcd(g,a);
                }
                if(g!=1){
                    sc.println(1);
                    sc.println(g);
                }
                else{
                    Set<Integer> set=new HashSet<>();
                    for(int a:arr){
                        for(int j=2;j*j<=a;j++){
                            if(prime[j]&&a%j==0){
                                set.add(j);
                                while(a%j==0){
                                    a/=j;
                                }
                            }
                        }
                        if(a>1){
                            set.add(a);
                        }
                    }
                    int idx=-1;
                    for(int j=2;j<=x;j++){
                        if(prime[j]&&!set.contains(j)){
                            idx=j;
                            break;
                        }
                    }
                    if(idx==-1){
                        sc.println(2);
                        sc.println("2 3");
                    }
                    else{
                        sc.println(1);
                        sc.println(idx);
                    }
                }
            }
        }
        //sc.print(0);
        sc.bw.flush();
        sc.bw.close();
    }
    static int gcd(int a,int b){
        return a<b?gcd(b,a):a%b==0?b:gcd(a%b,b);
    }
}

class Read{
    BufferedReader bf;
    StringTokenizer st;
    BufferedWriter bw;
    public Read(){
        bf=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer("");
        bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public String nextLine() throws IOException{
        return bf.readLine();
    }
    public String next() throws IOException{
        while(!st.hasMoreTokens()){
            st=new StringTokenizer(bf.readLine());
        }
        return st.nextToken();
    }
    public char nextChar() throws IOException{
        return next().charAt(0);
    }
    public int nextInt() throws IOException{
        return Integer.parseInt(next());
    }
    public long nextLong() throws IOException{
        return Long.parseLong(next());
    }
    public double nextDouble() throws IOException{
        return Double.parseDouble(next());
    }
    public float nextFloat() throws IOException{
        return Float.parseFloat(next());
    }
    public byte nextByte() throws IOException{
        return Byte.parseByte(next());
    }
    public short nextShort() throws IOException{
        return Short.parseShort(next());
    }
    public BigInteger nextBigInteger() throws IOException{
        return new BigInteger(next());
    }
    public void println(int a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
    public void print(int a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void println(String a) throws IOException{
        bw.write(a);
        bw.newLine();
        return;
    }
    public void print(String a) throws IOException{
        bw.write(a);
        return;
    }
    public void println(long a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
    public void print(long a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void println(double a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
    public void print(double a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void print(BigInteger a) throws IOException{
        bw.write(a.toString());
        return;
    }
    public void print(char a) throws IOException{
        bw.write(String.valueOf(a));
        return;
    }
    public void println(char a) throws IOException{
        bw.write(String.valueOf(a));
        bw.newLine();
        return;
    }
}
