import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Math.*;
class SPTREE2
{
	List<Integer>[] tree;
	int[] dep,Par[],F,tin,tout,sp;
	int N,K,A,M=20,timer;
	Set<Integer> spl_set;
    void run() {
        int tc=ni();
        StringBuilder res = new StringBuilder();
        while(tc-->0) {
        	StringBuilder sb1 = new StringBuilder(),sb2 = new StringBuilder();
			N=ni(); K=ni(); A=ni();
			tree = new ArrayList[N+1];            
			dep = new int[N+1];
			tin = new int[N+1];
			tout = new int[N+1];
			sp = new int[N+1];
			Par = new int[N+1][M];
			spl_set = new HashSet<>();
			F = ni(K);

			for(int i=0;i<=N;i++) tree[i] = new ArrayList<>();

			for(int i=0;i<N-1;i++) {
				int u=ni(),v=ni();
				tree[u].add(v);
				tree[v].add(u);
			}
            timer=0;
            tin[0]=0; tout[0]=N+1;
			dfs(A,0);
// 			out.println(Arrays.toString(sp));
			dfs2(A,0);
// 			out.println(Arrays.toString(sp));
            // Map<Integer,Map<Integer,Integer>> map = new HashMap<>();
			for(int b=1;b<=N;b++) {
				int mx=-1,u=-1;
				if(sp[b]>0) { mx=dep[b]; u=sp[b]; }
				else {
				    int node = -1*sp[b];
				    u=sp[node];
				    mx=2*dep[node]-dep[b];
				}
				// for(int k=0;k<K;k++) {
				// 	int lca = lca_using_time(b,F[k]);
				// 	int d = dep[lca] - abs(dep[b]-dep[lca]);
				// 	if(mx<d) { mx=d; u=F[k]; }
				// }
				sb1.append(mx).append(" ");
				sb2.append(u).append(" ");
			}
			res.append(sb1).append("\n").append(sb2).append("\n");
        }
        out.println(res);
        out.flush(); 
        out.close();
    }

    int dist(int u, int v) {
    	return dep[u]+dep[v]-(dep[lca_using_time(u,v)]<<1);
    }
    
    void dfs2(int cur, int par) {
        for(int child:tree[cur]) {
            if(child!=par) {
                if(sp[child]==0) sp[child]=sp[cur]>0?-cur:sp[cur];
                dfs2(child,cur);
            }
        }
    }

    void dfs(int cur, int par) {
    // 	Par[cur][0]=par;
    // 	tin[cur]=++timer;
    	if(spl_set.contains(cur)) sp[cur]=cur;
    // 	for(int j=1;j<M;j++) Par[cur][j] = Par[Par[cur][j-1]][j-1];

    	for(int child:tree[cur]) {
    		if(child!=par) {
    			dep[child] = dep[cur]+1;
    			dfs(child,cur);
    			sp[cur]=sp[child]!=0?sp[child]:sp[cur];
    		}
    	}
    // 	tout[cur]=timer;
    }
    boolean isAncestor(int u, int v) {
    	return tin[u]<=tin[v] && tout[u]>=tout[v];
    }

    int lca_using_time(int u, int v) {
    	if(isAncestor(u,v)) return u;
    	if(isAncestor(v,u)) return v;
    	for(int i=M-1;i>=0;i--) {
    		if(!isAncestor(Par[u][i],v)) {
    			u=Par[u][i];
    		}
    	}
    	return Par[u][0];
    }
    int lca_(int u, int v) {
    	if(u==v) return u;

    	if(dep[u]<dep[v]) u^=v^(v=u);

    	int diff = dep[u]-dep[v];
    	for(int i=M-1;i>=0;i--) {
    		if(((diff>>i)&1)!=0) {
    			u = Par[u][i];
    		}
    	}

    	if(u==v) return u;

		for(int i=M-1;i>=0;i--) {
    		if(Par[u][i]!=Par[v][i]) {
    			u = Par[u][i];
    			v = Par[v][i];
    		}
    	}

    	return Par[u][0];

    }

    public static void main(String[] args)throws Exception {
        try { 
            new SPTREE2("LOL").run();
            // new Thread(null, new SPTREE2()::run, "1", 1 << 26).start();
            // new Thread(null, new SPTREE2("ONLINE_JUDGE")::run, "1", 1 << 26).start();
        } catch(Exception e) {}
    }

    FastReader sc=null;PrintWriter out = null;
    public SPTREE2()throws Exception {
        sc = new FastReader(new FileInputStream("input.txt"));
        out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
    }
    public SPTREE2(String JUDGE) {
        sc = new FastReader(System.in);
        out = new PrintWriter(System.out);      
    }
    
    String ns() { return sc.next(); }
    int ni() { return sc.nextInt(); }
    long nl() { return sc.nextLong(); }
    int[] ni(int n) {
        int a[]=new int[n];
        for(int i=0;i<n;a[i]=ni(),spl_set.add(a[i++]));
        return a;
    }
    long[] nl(int n) {
        long a[]=new long[n];
        for(int i=0;i<n;a[i++]=nl());
        return a;
    }
    
    int[][] ni(int n,int m) {
        int a[][]=new int[n][m];
        for(int i=0;i<n;i++) 
            for(int j=0;j<m;j++)
                a[i][j]=ni();
        return a;
    }
    long[][] nl(int n,int m) {
        long a[][]=new long[n][m];
        for(int i=0;i<n;i++) 
            for(int j=0;j<m;j++)
                a[i][j]=nl();
        return a;
    }
    int gcd(int a, int b) {
        return b==0?a:gcd(b,a%b);
    }
    static class FastReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private FastReader.SpaceCharFilter filter;
        public FastReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }
        
        public long nextLong() {
            int c = read();
            while (isSpaceChar(c)) c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9') throw new InputMismatchException();
                res = res*1L*10;
                res += c - '0';
                c = read();
            }
            while (!isSpaceChar(c));
            return res *1L* sgn;
        }
        
        public String next() {
            int c = read();
            while (isSpaceChar(c)) c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }
    }
}
