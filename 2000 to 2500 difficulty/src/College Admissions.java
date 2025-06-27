import java.io.*;

class Codechef {
    static class FastReader {
        final int BUFFER_SIZE = 1 << 16;
        DataInputStream din;
        byte[] buffer;
        int bufferPointer, bytesRead;
        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = false;
            if (c == '-') { neg = true; c = read(); }
            do {
                ret = ret * 10 + (c - '0');
                c = read();
            } while (c >= '0' && c <= '9');
            return neg ? -ret : ret;
        }
        private byte read() throws IOException {
            if (bytesRead == -1) throw new EOFException();
            if (bufferPointer == bytesRead) {
                bytesRead = din.read(buffer, 0, BUFFER_SIZE);
                bufferPointer = 0;
                if (bytesRead == -1) return -1;
            }
            return buffer[bufferPointer++];
        }
    }

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = fr.nextInt();

        while (T-- > 0) {
            int N = fr.nextInt(), M = fr.nextInt();
            int[] collegeRank = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                collegeRank[i] = fr.nextInt();
            }

            // posOfRank[r] = student ID whose exam‐rank is r
            int[] posOfRank = new int[M + 1];
            for (int i = 1; i <= M; i++) {
                posOfRank[fr.nextInt()] = i;
            }

            // read each student's preference list
            int[][] prefs = new int[M + 1][];
            for (int i = 1; i <= M; i++) {
                int k = fr.nextInt();
                int[] p = new int[k];
                for (int j = 0; j < k; j++) {
                    p[j] = fr.nextInt();
                }
                prefs[i] = p;
            }

            boolean[] filled = new boolean[N + 1];
            int chefResult = 0;

            // process students in ascending exam‐rank order
            for (int r = 1; r <= M; r++) {
                int sid = posOfRank[r];
                int bestC = 0, bestR = Integer.MAX_VALUE;
                for (int c : prefs[sid]) {
                    if (!filled[c] && collegeRank[c] < bestR) {
                        bestR = collegeRank[c];
                        bestC = c;
                    }
                }
                if (bestC > 0) filled[bestC] = true;
                if (sid == 1) {           // Chef's turn
                    chefResult = bestC;  // 0 if none available
                    break;
                }
            }

            bw.write(chefResult + "\n");
        }
        bw.flush();
    }
}

/*

Explanation of code : 

This Java code solves a college allocation problem involving student preferences and college rankings, using efficient I/O and a greedy approach. 

🧠 Problem Idea:
You are given:

N colleges with their ranks (collegeRank[1...N])

M students with their exam ranks and college preference lists.

Students are assigned to their most preferred available college with the best (lowest) rank.

Chef is always student ID 1. You need to output which college Chef gets after all higher-ranked students have chosen.

🛠️ Code Overview:
Fast I/O Classes:

FastReader is used for fast input via DataInputStream.

BufferedWriter is used for fast output.

Input Parsing:

collegeRank[i]: rank of the i-th college.

posOfRank[r]: student ID whose exam-rank is r.

prefs[i]: preference list of student i.

Student Processing:

For each student in exam-rank order (from 1 to M):

Choose the best unfilled college from their preferences (lowest college rank).

Mark that college as filled.

If it’s Chef’s turn (student ID 1), store and print his allocated college.

✅ Key Points:
Greedy selection ensures students get their best possible college.

Chef’s result is output immediately once he is processed.

Optimized for large inputs.

This approach efficiently simulates a preference-based allocation system using simple arrays and conditions.

*/
