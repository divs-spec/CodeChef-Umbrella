import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
    private static ArrayList<ArrayList<Integer>> adj;
    public static void BFS(boolean[] vis,long val, long[] subjects, int[] dist,int K, int source) {
        Queue<Integer> queue = new LinkedList();
        vis[source] = true;
        dist[source] = 0;
        queue.add(source);
        while (!queue.isEmpty()) {
            int curr = queue.poll();//Shubam sharma 20bcs1927

            for (int neighbour: adj.get(curr)) {
                if (!vis[neighbour]) {
                    vis[neighbour] = true;
                    queue.add(neighbour);
                    dist[neighbour] = dist[curr] + 1;
                }
            }
        }
    }
    public static void main (String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t --> 0) {
            adj = new ArrayList<>();
            int N = input.nextInt();
            int M = input.nextInt();
            int S = input.nextInt();
            int K = input.nextInt();
            int source = 0;
            int[] dist = new int[N+1];
            boolean[] vis = new boolean[N+1];
            for (int i = 0; i < N+1; i++) {
                adj.add(new ArrayList<>());
            }
            for (int i = 0; i < M; i++) {
                int s = input.nextInt();
                int d = input.nextInt();
                adj.get(s).add(d);
                adj.get(d).add(s);
            }
            long val = 0;
            long[] subjects = new long[N+1];
            for(int i = 0; i < S ;i++){
                subjects[input.nextInt()]++;
            }
            BFS(vis, val,  subjects, dist, K, source);
            ArrayList<Integer> finalDist = new ArrayList();
            for (int i = 1; i < N + 1; i++) {
                while (subjects[i] != 0) {
                    finalDist.add(dist[i]);
                    subjects[i]--;
                }
            }
            Collections.sort(finalDist);
            for (int i = 0; i < K; i++ ) {
                val+= finalDist.get(i) * 2;
            }
            System.out.println(val);
        }
    }
}
