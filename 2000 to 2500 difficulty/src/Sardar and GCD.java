//package kg.my_algorithms.codechef;



import java.io.*;
import java.util.*;

public class Main {
    private static final long MOD = 998244353L;
    public static void main(String[] args) throws IOException {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        FastReader fr = new FastReader();
        StringBuilder sb = new StringBuilder();
        int testCases = fr.nextInt();
        for(int testCase=1;testCase<=testCases;testCase++){
            int n = fr.nextInt();
            List<List<Integer>> tree = new ArrayList<>();
            for(int i=0;i<n;i++) tree.add(new ArrayList<>());
            for(int i=0;i<n-1;i++){
                int u = fr.nextInt()-1;
                int v = fr.nextInt()-1;
                tree.get(u).add(v);
                tree.get(v).add(u);
            }
            int[] a = new int[n];
            for(int i=0;i<n;i++) a[i] = fr.nextInt();
            long[] answers = new long[n];
            int[] dp = new int[n];   //Can be memory efficient by using int[]
            getGCDInSubtree(0,-1,tree,a,dp);
            answers[0] = dp[0];
            fillAnswers(0,-1,a,0L,dp,tree,answers);
            long maxValue = -1L;
            for(long aa: answers) maxValue = Math.max(maxValue,aa);
  //          System.out.println("answers= " + Arrays.toString(answers));
            sb.append(maxValue).append("\n");
        }
        output.write(sb.toString());
        output.flush();
    }
    private static long getGCDExcept(int i, GCDSegmentTree gcdSegmentTree){
        if(gcdSegmentTree.n == 1) return 0;
        if(i == 0) return gcdSegmentTree.getGCDInclusiveRange(1,gcdSegmentTree.n-1);
        if(i == gcdSegmentTree.n-1) return gcdSegmentTree.getGCDInclusiveRange(0,gcdSegmentTree.n-2);
        return getGCD(gcdSegmentTree.getGCDInclusiveRange(0,i-1) , gcdSegmentTree.getGCDInclusiveRange(i+1,gcdSegmentTree.n-1));
    }
    private static long getGCDInSubtree(int node, int parent, List<List<Integer>> tree ,int[] a, int[] dp){
        int gcdInSubtree = a[node];
        for(int child: tree.get(node)){
            if(child == parent) continue;
            gcdInSubtree = (int)getGCD(getGCDInSubtree(child,node,tree,a,dp),gcdInSubtree);
        }
        return dp[node] = gcdInSubtree;
    }
    private static long getGCD(long a, long b){
        if(a%b == 0) return b;
        return getGCD(b,a%b);
    }
    private static void fillAnswers(int node,int parent,int[] a,long contributionFromParent, int[] dp,List<List<Integer>> tree,long[] answers){
        int numberOfChildren = tree.get(node).size()-1;
        if(parent == -1) numberOfChildren++;
        long contributionFromChildren = 0;
        int index = 0;
        int[] gcdOfChildren = new int[numberOfChildren];
        for(int child: tree.get(node)){
            if(child == parent) continue;
            gcdOfChildren[index] = dp[child];
            contributionFromChildren += gcdOfChildren[index++];
        }
        index = 0;
        answers[node] = contributionFromChildren+contributionFromParent;
//        System.out.println("contributionFromParent= " + contributionFromParent +"    at node= " + node);
//        System.out.println("gcdChildren = " + Arrays.toString(gcdOfChildren));
        if(numberOfChildren>0){
            GCDSegmentTree gcdSegmentTree = new GCDSegmentTree(gcdOfChildren);
            for(int child: tree.get(node)){
                if(child == parent) continue;
                long cur_contribution = getGCDExcept(index,gcdSegmentTree);
                if(contributionFromParent!=0) cur_contribution = getGCD(cur_contribution,contributionFromParent);
                fillAnswers(child,node,a,getGCD(cur_contribution,a[node]),dp,tree,answers);
                index++;
            }
        }
    }
}
class GCDSegmentTree{
    int[] sa;
    int n;

    public GCDSegmentTree(int[] arr){
        this.n = arr.length;
        int height = (int)Math.ceil(Math.log(n)/Math.log(2));
        int size = 2*(1<<height) - 1;
        this.sa = new int[size];
        constructor(0,n-1,0,arr);
    }
    private int constructor(int ss, int se, int si, int[] arr){
        if(ss == se) return sa[si] = arr[ss];
        int m = getMid(ss,se);
        return sa[si] = getGCD(constructor(ss,m,2*si+1,arr),constructor(m+1,se,2*si+2,arr));
    }
    private int getMid(int ss, int se){
        return (ss+se)>>1;
    }
    private int getGCD(int a, int b){
        if(a%b == 0) return b;
        return getGCD(b,a%b);
    }
    private int queryGCD(int aa, int bb){
        if(aa==0) return bb;
        if(bb==0) return aa;
        return getGCD(Math.max(aa,bb),Math.min(aa,bb));
    }
    public int getGCDInclusiveRange(int left, int right){
        return query(0,n-1,0,left,right);
    }
    private int query(int ss, int se, int si, int qs, int qe){
        if(se<qs || qe<ss) return 0;
        if(qs<=ss && qe>=se) return sa[si];
        int m = getMid(ss,se);
        return queryGCD(query(ss,m,2*si+1,qs,qe),query(m+1,se,2*si+2,qs,qe));
    }
}









class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() { return Integer.parseInt(next()); }

    long nextLong() { return Long.parseLong(next()); }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try {
            if(st.hasMoreTokens()){
                str = st.nextToken("\n");
            }
            else{
                str = br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
