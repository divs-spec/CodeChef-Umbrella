    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.util.*;

    import static java.lang.Math.*;
    import static java.util.Arrays.sort;


    public class Main {
        static FastReader fs;
        static PrintWriter out;
        static int mod = (int) 1e9 + 7;
        static long fact[], invfact[];
        static ArrayList<Integer> adj[];

        static long inf = (long) (9e18);
        public static void main(String[] args) {
            fs = new FastReader();
            out = new PrintWriter(System.out);
//            int tt = 1;
            int tt = fs.fi();
            int max = (int) 1e5+5;
            boolean prime [] = new boolean[max];
            Arrays.fill(prime,true);
            prime[1] = false;
            for(int i = 2; (long) i*i < max; i++){
                if(prime[i]){
                    for(int j = i*i ; j < max;j+=i){
                        prime[j] = false;
                    }
                }

            }
            for (int test = 1; test <= tt; test++) {
                int n = fs.fi();
                int k = fs.fi();
                int cnt = 1;
                HashSet<Integer> set = new HashSet<>();
                set.add(1);

                for(int i = 2; i <= n; i++){
                    if(prime[i] && i*2 > n){
                        cnt++;
                        set.add(i);
                    }
                }

                if(n-cnt <= k || cnt >= k){
                    out.println("Yes");
                    int count = 0;
                    if(k <= cnt){
                        for(int i : set){
                            out.print(i + " ");
                            count++;
                            if(count == k) break;
                        }
                        out.println();
                    }else {
                        for(int i = 1; i <= n; i++){
                            if(!set.contains(i)){
                                out.print(i + " ");
                                count++;
                            }
                        }
                        for(int i : set){
                            if(count == k) break;
                            out.print(i + " ");
                            count++;

                        }
                        out.println();
                    }

                }else {
                    out.println("No");
                }
            }
            out.close();

        }




        static class SegmentTreeMin {
            long[] tree;
            long[] nums;
            int n;

            SegmentTreeMin(long[] nums) {
                this.nums = nums;
                this.n = nums.length;
                this.tree = new long[4 * n];
                for (int i = 0; i < 4 * n; i++) tree[i] = inf;
                buildTree(1, 0, n - 1);
            }

            void buildTree(int node, int start, int end) {
                if (start == end) tree[node] = nums[start];
                else {
                    int mid = (start + end) / 2;
                    buildTree(2 * node, start, mid);
                    buildTree(2 * node + 1, mid + 1, end);
                    tree[node] = min(tree[2 * node], tree[2 * node + 1]);
                }
            }

            long query(int left, int right) {
                return query(1, 0, n - 1, left - 1, right - 1);
            }

            long query(int node, int start, int end, int left, int right) {
                if (right < start || left > end) return inf;
                else if (left <= start && right >= end) return tree[node];
                else
                    return min(query(2 * node, start, (start + end) / 2, left, right), query(2 * node + 1, ((start + end) / 2) + 1, end, left, right));
            }

            void update(int index, long value) {
                update(1, 0, n - 1, index - 1, value);
            }

            void update(int node, int start, int end, int index, long value) {
                if (start == end) {
                    nums[index] = value;
                    tree[node] = value;
                } else {
                    int mid = (start + end) / 2;
                    if (index >= start && index <= mid) update(2 * node, start, mid, index, value);
                    else update(2 * node + 1, mid + 1, end, index, value);
                    tree[node] = min(tree[2 * node], tree[2 * node + 1]);
                }
            }
        }


        static class Pair2 {
            String s;
            long count;

            public Pair2(String s, long count) {
                this.s = s;

                this.count = count;
            }

        }

        static class MergeSortTree {
            int n;
            List<Integer>[] tree;

            public MergeSortTree(int[] a) {
                this.n = a.length;
                int size = 1;
                while (size < n) {
                    size *= 2;
                }
                size *= 2;
                tree = new ArrayList[size];
                for (int i = 0; i < size; i++) {
                    tree[i] = new ArrayList<>();
                }
                build(a, 0, 0, n);
                for (int i = 0; i < size; i++) {
                    // if (test) System.out.format("  i:%2d %s\n", i, trace(tree[i]));
                }
            }

            void build(int[] a, int x, int l, int r) {
                if (l + 1 == r) {
                    tree[x].add(a[l]);
                    return;
                }
                int m = (l + r) / 2;
                build(a, 2 * x + 1, l, m);
                build(a, 2 * x + 2, m, r);
                merge(tree[2 * x + 1], tree[2 * x + 2], tree[x]);
            }

            int count(int lq, int rq, int mn, int mx, int x, int l, int r) {
                if (rq <= l || r <= lq) {
                    return 0;
                }
                if (lq <= l && r <= rq) {
                    int e = Collections.binarySearch(tree[x], mx);
                    if (e < 0) {
                        e = -(e + 1);
                    }
                    int b = Collections.binarySearch(tree[x], mn);
                    if (b < 0) {
                        b = -(b + 1);
                    }
                    return e - b;
                }

                int m = (l + r) / 2;
                int a = count(lq, rq, mn, mx, 2 * x + 1, l, m);
                int b = count(lq, rq, mn, mx, 2 * x + 2, m, r);
                return a + b;
            }

            int count(int lq, int rq, int mn, int mx) {
                return count(lq, rq, mn, mx, 0, 0, n);
            }

            static void merge(List<Integer> a, List<Integer> b, List<Integer> out) {
                int i = 0;
                int j = 0;
                while (i < a.size() && j < b.size()) {
                    if (a.get(i) <= b.get(j)) {
                        out.add(a.get(i++));
                    } else {
                        out.add(b.get(j++));
                    }
                }
                while (i < a.size()) {
                    out.add(a.get(i++));
                }
                while (j < b.size()) {
                    out.add(b.get(j++));
                }
            }
        }


        static boolean found = false;

        static class Union {
            int[] id, sz;
            int comps;

            // 0 to n
            public Union(int n) {
                n--;
                id = new int[n + 1];
                sz = new int[n + 1];
                comps = n + 1;
                for (int i = 0; i < n + 1; i++) {
                    id[i] = i;
                }
                Arrays.fill(sz, 1);
            }

            public int find(int a) {
                return id[a] = (id[a] == a ? a : find(id[a]));
            }

            public boolean con(int a, int b) {
                return find(a) == find(b);
            }

            public int size(int a) {
                return sz[find(a)];
            }

            public int comps() {
                return comps;
            }

            public void unify(int a, int b) {
                a = find(a);
                b = find(b);
                if (a == b) return;
                if (sz[a] >= sz[b]) {
                    id[b] = a;
                    sz[a] += sz[b];
                } else {
                    id[a] = b;
                    sz[b] += sz[a];
                }
                comps--;
            }
        }

        public static void cal(int n) {
            fact = new long[n];
            invfact = new long[n];
            fact[0] = 1;
            invfact[0] = 1;
            for (int i = 1; i < n; i++) {
                fact[i] = (fact[i - 1] * i) % mod;
                invfact[i] = (modInverse(fact[i]));
            }


        }


        public static long NCR(int n, int r) {
            if (r < 0 || n < 0) {
                return -1;
            }
            if (n < r) return 0;
            if (n == 0 || r == n) return 1;
            return (fact[n] * (invfact[r]) % mod) * invfact[n - r] % mod;
        }

        public static long modInverse(long n) {
            return powMod(n, mod - 2);
        }

        public static long powMod(long x, long y) {
            long res = 1;
            x = x % mod;
            while (y > 0) {
                if ((y & 1) == 1) {
                    res = (res * x) % mod;
                }
                y = y >> 1;
                x = (x * x) % mod;
            }
            return res;
        }


        public static long[] swap(long a, long b) {
            return new long[]{b, a};
        }


        static class Pair implements Comparable<Pair> {
            int start;
            int end;

            Pair(int first, int second) {
                this.start = first;
                this.end = second;
            }

            @Override
            public int compareTo(Pair a) {
                return this.start - a.end;
            }
        }


        static class Node {

            int val;
            int index;

            public Node(int val, int index) {
                this.val = val;
                this.index = index;
            }
        }


        // constants
        static final int IBIG = 1000000007;
        static final int IMAX = 2147483647;
        static final long LMAX = 9223372036854775807L;
        static Random __r = new Random();
        static int[][] direction = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};


        // math util
        static int minof(int a, int b, int c) {
            return min(a, min(b, c));
        }


        static int minof(int... x) {
            if (x.length == 1)
                return x[0];
            if (x.length == 2)
                return min(x[0], x[1]);
            if (x.length == 3)
                return min(x[0], min(x[1], x[2]));
            int min = x[0];
            for (int i = 1; i < x.length; ++i)
                if (x[i] < min)
                    min = x[i];
            return min;
        }

        static char maxc(char a, char b) {
            if (a > b) {
                return a;
            }
            return b;
        }

        static long minof(long a, long b, long c) {
            return min(a, min(b, c));
        }

        static long minof(long... x) {
            if (x.length == 1)
                return x[0];
            if (x.length == 2)
                return min(x[0], x[1]);
            if (x.length == 3)
                return min(x[0], min(x[1], x[2]));
            long min = x[0];
            for (int i = 1; i < x.length; ++i)
                if (x[i] < min)
                    min = x[i];
            return min;
        }

        static int maxof(int a, int b, int c) {
            return max(a, max(b, c));
        }

        static int maxof(int... x) {
            if (x.length == 1)
                return x[0];
            if (x.length == 2)
                return max(x[0], x[1]);
            if (x.length == 3)
                return max(x[0], max(x[1], x[2]));
            int max = x[0];
            for (int i = 1; i < x.length; ++i)
                if (x[i] > max)
                    max = x[i];
            return max;
        }

        static long maxof(long a, long b, long c) {
            return max(a, max(b, c));
        }

        static long maxof(long... x) {
            if (x.length == 1)
                return x[0];
            if (x.length == 2)
                return max(x[0], x[1]);
            if (x.length == 3)
                return max(x[0], max(x[1], x[2]));
            long max = x[0];
            for (int i = 1; i < x.length; ++i)
                if (x[i] > max)
                    max = x[i];
            return max;
        }

        static int powi(int a, int b) {
            if (a == 0)
                return 0;
            int ans = 1;
            while (b > 0) {
                if ((b & 1) > 0)
                    ans *= a;
                a *= a;
                b >>= 1;
            }
            return ans;
        }

        static long powl(long a, int b) {
            if (a == 0)
                return 0;
            long ans = 1;
            while (b > 0) {
                if ((b & 1) > 0)
                    ans *= a;
                a *= a;
                b >>= 1;
            }
            return ans;
        }

        static int fli(double d) {
            return (int) d;
        }

        static int cei(double d) {
            return (int) ceil(d);
        }

        static long fll(double d) {
            return (long) d;
        }

        static long cel(double d) {
            return (long) ceil(d);
        }

        static int gcd(int a, int b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        static int lcm(int a, int b) {
            return (a / gcd(a, b)) * b;
        }

        static long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        static long lcm(long a, long b) {
            return (a / gcd(a, b)) * b;
        }

        static int[] exgcd(int a, int b) {
            if (b == 0)
                return new int[]{1, 0};
            int[] y = exgcd(b, a % b);
            return new int[]{y[1], y[0] - y[1] * (a / b)};
        }

        static long[] exgcd(long a, long b) {
            if (b == 0)
                return new long[]{1, 0};
            long[] y = exgcd(b, a % b);
            return new long[]{y[1], y[0] - y[1] * (a / b)};
        }

        static int randInt(int min, int max) {
            return __r.nextInt(max - min + 1) + min;
        }

        static long mix(long x) {
            x += 0x9e3779b97f4a7c15L;
            x = (x ^ (x >> 30)) * 0xbf58476d1ce4e5b9L;
            x = (x ^ (x >> 27)) * 0x94d049bb133111ebL;
            return x ^ (x >> 31);
        }

        public static boolean[] findPrimes(int limit) {
            assert limit >= 2;

            final boolean[] nonPrimes = new boolean[limit];
            nonPrimes[0] = true;
            nonPrimes[1] = true;

            int sqrt = (int) Math.sqrt(limit);
            for (int i = 2; i <= sqrt; i++) {
                if (nonPrimes[i])
                    continue;
                for (int j = i; j < limit; j += i) {
                    if (!nonPrimes[j] && i != j)
                        nonPrimes[j] = true;
                }
            }

            return nonPrimes;
        }

        // array util
        static void reverse(int[] a) {
            for (int i = 0, n = a.length, half = n / 2; i < half; ++i) {
                int swap = a[i];
                a[i] = a[n - i - 1];
                a[n - i - 1] = swap;
            }
        }

        static void reverse(long[] a) {
            for (int i = 0, n = a.length, half = n / 2; i < half; ++i) {
                long swap = a[i];
                a[i] = a[n - i - 1];
                a[n - i - 1] = swap;
            }
        }

        static void reverse(double[] a) {
            for (int i = 0, n = a.length, half = n / 2; i < half; ++i) {
                double swap = a[i];
                a[i] = a[n - i - 1];
                a[n - i - 1] = swap;
            }
        }

        static void reverse(char[] a) {
            for (int i = 0, n = a.length, half = n / 2; i < half; ++i) {
                char swap = a[i];
                a[i] = a[n - i - 1];
                a[n - i - 1] = swap;
            }
        }

        static void shuffle(int[] a) {
            int n = a.length - 1;
            for (int i = 0; i < n; ++i) {
                int ind = randInt(i, n);
                int swap = a[i];
                a[i] = a[ind];
                a[ind] = swap;
            }
        }

        static void shuffle(long[] a) {
            int n = a.length - 1;
            for (int i = 0; i < n; ++i) {
                int ind = randInt(i, n);
                long swap = a[i];
                a[i] = a[ind];
                a[ind] = swap;
            }
        }

        static void shuffle(double[] a) {
            int n = a.length - 1;
            for (int i = 0; i < n; ++i) {
                int ind = randInt(i, n);
                double swap = a[i];
                a[i] = a[ind];
                a[ind] = swap;
            }
        }

        static void rsort(int[] a) {
            shuffle(a);
            sort(a);
        }

        static void msort(int a[][]) {
            Arrays.sort(a, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return 0;
                }
            });
        }

        static PriorityQueue<int[]> funcPQ() {
            return new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return 0;
                }
            });
        }

        static void rsort(long[] a) {
            shuffle(a);
            sort(a);
        }

        static void rsort(double[] a) {
            shuffle(a);
            sort(a);
        }

        static int[] copy(int[] a) {
            int[] ans = new int[a.length];
            for (int i = 0; i < a.length; ++i)
                ans[i] = a[i];
            return ans;
        }

        static long[] copy(long[] a) {
            long[] ans = new long[a.length];
            for (int i = 0; i < a.length; ++i)
                ans[i] = a[i];
            return ans;
        }

        static double[] copy(double[] a) {
            double[] ans = new double[a.length];
            for (int i = 0; i < a.length; ++i)
                ans[i] = a[i];
            return ans;
        }

        static char[] copy(char[] a) {
            char[] ans = new char[a.length];
            for (int i = 0; i < a.length; ++i)
                ans[i] = a[i];
            return ans;
        }

        static class FastReader {

            BufferedReader br;
            StringTokenizer st;

            public FastReader() {
                br = new BufferedReader(
                        new InputStreamReader(System.in));
            }

            String next() {
                while (st == null || !st.hasMoreElements()) {
                    try {
                        st = new StringTokenizer(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return st.nextToken();
            }

            int fi() {
                return Integer.parseInt(next());
            }

            int[] ria(int n) {
                int[] a = new int[n];
                for (int i = 0; i < n; i++)
                    a[i] = Integer.parseInt(next());
                return a;
            }

            long fl() {
                return Long.parseLong(next());
            }

            long[] rla(int n) {
                long[] a = new long[n];
                for (int i = 0; i < n; i++)
                    a[i] = Long.parseLong(next());
                return a;
            }

            double fd() {
                return Double.parseDouble(next());
            }

            String nextLine() {
                String str = "";
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return str;
            }
        }
    }
