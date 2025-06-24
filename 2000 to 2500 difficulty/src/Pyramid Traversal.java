import java.util.*;
import java.io.*;
class PYRAMIDMOVES{
    //SOLUTION BEGIN
    int MOD = (int)1e9+7;
    long[][] fif = fif(100000);
    void pre() throws Exception{}
    void solve(int TC) throws Exception{
        int s = ni(), e = ni();
        int[] p1 = pos(s), p2 = pos(e);
        int N = p2[0]-p1[0], R = p2[1]-p1[1];
        if(N < R || R < 0)pn(0);
        else pn(C(N, R));
    }
    int[] pos(int num){
        int r = 0;
        while(((r+2)*(r+1))/2 < num)r++;
        return new int[]{r+1, num-(r*(r+1))/2};
    }
    long[][] fif(int mx){
        mx++;
        long[] F = new long[mx], IF = new long[mx];
        F[0] = 1;
        for(int i = 1; i< mx; i++)F[i] = (F[i-1]*i)%MOD;
        //GFG
        long M = MOD; 
        long y = 0, x = 1; 
        long a = F[mx-1];
        while(a> 1){ 
            long q = a/M;
            long t = M; 
            M = a%M;
            a = t;
            t = y;
            y = x-q*y;
            x = t;
        } 
        if(x<0)x+=MOD;
        IF[mx-1] = x;
        for(int i = mx-2; i>= 0; i--)IF[i] = (IF[i+1]*(i+1))%MOD;
        return new long[][]{F, IF};
    }
    long C(int n, int r){
        if(n<r || r<0)return 0;
        return (fif[0][n]*((fif[1][r]*fif[1][n-r])%MOD))%MOD;
    }
    //SOLUTION END
    void hold(boolean b)throws Exception{if(!b)throw new Exception("Hold right there, Sparky!");}
    static boolean multipleTC = true;
    FastReader in;PrintWriter out;
    void run() throws Exception{
        in = new FastReader();
        out = new PrintWriter(System.out);
        //Solution Credits: Taranpreet Singh
        int T = (multipleTC)?ni():1;
        pre();for(int t = 1; t<= T; t++)solve(t);
        out.flush();
        out.close();
    }
    public static void main(String[] args) throws Exception{
        new PYRAMIDMOVES().run();
    }
    int bit(long n){return (n==0)?0:(1+bit(n&(n-1)));}
    void p(Object o){out.print(o);}
    void pn(Object o){out.println(o);}
    void pni(Object o){out.println(o);out.flush();}
    String n()throws Exception{return in.next();}
    String nln()throws Exception{return in.nextLine();}
    int ni()throws Exception{return Integer.parseInt(in.next());}
    long nl()throws Exception{return Long.parseLong(in.next());}
    double nd()throws Exception{return Double.parseDouble(in.next());}

    class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws Exception{
            br = new BufferedReader(new FileReader(s));
        }

        String next() throws Exception{
            while (st == null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                }catch (IOException  e){
                    throw new Exception(e.toString());
                }
            }
            return st.nextToken();
        }

        String nextLine() throws Exception{
            String str = "";
            try{   
                str = br.readLine();
            }catch (IOException e){
                throw new Exception(e.toString());
            }  
            return str;
        }
    }
}
