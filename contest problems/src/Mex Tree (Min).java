import java.io.*;
import java.util.*;

class CodeChef {

    // Fast Input and Output
    static class FastIO {
        BufferedReader br;
        StringTokenizer st;

        public FastIO() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
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

        void print(String s) {
            System.out.print(s);
        }

        void println(String s) {
            System.out.println(s);
        }
    }

    // Debug Function
    static void debug(Object... o) {
        System.err.println(Arrays.deepToString(o));
    }

    // GCD
    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // LCM
    static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    // Prime Check
    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    // Sieve of Eratosthenes
    static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    // Modular Exponentiation
    static long modExp(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp >>= 1;
        }
        return result;
    }

    // Factorial Modulo
    static long factorialMod(int n, int mod) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % mod;
        }
        return result;
    }

    // Custom Comparator for Sorting
    static class Pair implements Comparable<Pair> {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return Integer.compare(this.first, o.first); // Ascending order by first
        }
    }

    // Prefix Sum
    static int[] prefixSum(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n];
        prefix[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        return prefix;
    }

    // Binary Search
    static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; // Not found
    }

    
     static List<List<Integer>> map;
    static int[] value;      
    static boolean[] seen;    
    static List<Integer> path; 
    static long ans;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            map = new ArrayList<>();
            for (int i = 0; i < n; i++) map.add(new ArrayList<>());

            int[] degree = new int[n];
            // read edges
            for (int i = 0; i < n-1; i++) {
                String[] s = br.readLine().split(" ");
                int u = Integer.parseInt(s[0]) - 1;
                int v = Integer.parseInt(s[1]) - 1;
                map.get(u).add(v);
                map.get(v).add(u);
                degree[u]++;
                degree[v]++;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a,b) -> a[1]==b[1] ? b[0]-a[0] : b[1]-a[1]
            );
            for (int i = 0; i < n; i++) {
                pq.offer(new int[]{i, degree[i]});
            }

           
            value = new int[n];
            int label = n - 1;
            while (!pq.isEmpty()) {
                int node = pq.poll()[0];
                value[node] = label--;
            }

            
            seen = new boolean[n+1];
            path = new ArrayList<>();
            ans = 0;

            for (int u = 0; u < n; u++) {
                dfsScore(u, -1, u);
            }

            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    static void dfsScore(int u, int p, int start) {
        path.add(value[u]);

        if (u >= start) {
            Arrays.fill(seen, false);
            for (int x : path) {
                if (x < seen.length) seen[x] = true;
            }
            int mex = 0;
            while (mex < seen.length && seen[mex]) mex++;
            ans += mex;
        }

        for (int v : map.get(u)) {
            if (v == p) continue;
            dfsScore(v, u, start);
        }

        path.remove(path.size() - 1);
    }
    
}
