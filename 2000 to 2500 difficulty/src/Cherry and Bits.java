
import java.util.*;
import java.io.*;

class Codechef {
  public static void main(String[] args) {
    try {
      FastReader in = new FastReader();
      FastWriter out = new FastWriter();
      int testCases = 1;

      /*------------------------------------------- for each test case ---------------------------------------*/
      while (testCases-- > 0) {
        /*--------------------------------------- fixed inputs ----------------------------------------------*/

        // int n=in.nextInt();
        // out.println(getRes(n));

        /*-------------------------------------- variable inputs ---------------------------------------------*/

        int n = in.nextInt();
        int m = in.nextInt();
        int a[][] = new int[n + 1][m + 1];
        int p[][] = new int[n + 2][m + 2];

        /*--------- for each input --------*/
        for (int i = 1; i <= n; i++) {
          String s = in.nextLine();
          for (int j = 1; j <= m; j++)
            a[i][j] = s.charAt(j - 1) - '0';
        }
        int q = in.nextInt();
        for (int i = 0; i < q; i++) {
          int w = in.nextInt();
          int x = in.nextInt();
          int y = in.nextInt();
          int z = in.nextInt();
          p[w][x]++;
          p[w][z + 1]--;
          p[y + 1][x]--;
          p[y + 1][z + 1]++;
        }
        for (int i = 1; i <= n; i++) {
          for (int j = 1; j <= m; j++) {
            p[i][j] += p[i - 1][j] + p[i][j - 1] - p[i - 1][j - 1];
            out.print((a[i][j] + p[i][j]) % 2);
          }
          out.println("");
        }
        // out.println(getRes(a, n));

        /*---------------------------------*/

      }
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static long getRes(int[] a, int n) {
    // code
    return 0;
  }

  /*-------------------------------------- read write classes ---------------------------------------------*/

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
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

    char nextChar() {
      return next().charAt(0);
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine().trim();
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

}
