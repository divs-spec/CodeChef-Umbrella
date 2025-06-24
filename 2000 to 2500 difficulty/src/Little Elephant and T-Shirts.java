//cook your dish here
import java.io.*;
import java.util.*;

class Solution{
    /*public static int findArrangementsUtil(HashMap<String,Integer> hm, HashSet<Integer> set, int person, int arr[][],int n){
        
        if(person == n)
            return 1;
        
        String key = person + "," + set.toString();
        if(hm.containsKey(key))
            return hm.get(key);
            
        int count = 0;
        for(int j=0;j<arr[person].length;j++){
            if(!set.contains(arr[person][j])){
                set.add(arr[person][j]);
                count += findArrangementsUtil(hm,set,person+1,arr,n);
                set.remove(arr[person][j]);
            }
        }
        hm.put(key,count);
        return count;
    }
    public static int findArrangements(int arr[][],int n){
        return findArrangementsUtil(new HashMap<>(),new HashSet<>(),0,arr,n);
    }*/
    
    /*public static int findArrangementsUtil(HashMap<String, Integer> hm, BitSet bitset, int person, int[][] arr, int n) {
        if (person == n) return 1;

        // Create a unique key based on the person index and the bitset state
        String key = person + "," + bitset.toString();
        if (hm.containsKey(key))
            return hm.get(key);

        int count = 0;
        for (int j = 0; j < arr[person].length; j++) {
            int val = arr[person][j];
            if (!bitset.get(val)) {
                bitset.set(val);
                count += findArrangementsUtil(hm, bitset, person + 1, arr, n);
                bitset.clear(val); // backtrack
            }
        }

        hm.put(key, count);
        return count;
    }

    public static int findArrangements(int[][] arr, int n) {
        return findArrangementsUtil(new HashMap<>(), new BitSet(101), 0, arr, n);
    }*/
    
    public static int findArrangementsUtil(int dp[][], int arr[][], int tShirt, int mask, int n){
        if(mask == (1<<n)-1)
            return 1;
        if(tShirt > 100)
            return 0;
        
        if(dp[tShirt][mask] != -1)
            return dp[tShirt][mask];
        
        int count = findArrangementsUtil(dp,arr,tShirt+1,mask,n);
        
        for(int i=0;i<n;i++){
            if(arr[i][tShirt] == 1 && ((mask & (1<<i))) == 0){
                count = (count + findArrangementsUtil(dp,arr,tShirt+1,mask|(1<<i),n))%1000000007;
            }
        }
        return dp[tShirt][mask] = count;
    }
    public static int findArrangements(int arr[][],int n){
        int dp[][] = new int[101][1<<n];
        for(int i=0;i<dp.length;i++)
            Arrays.fill(dp[i],-1);
        return findArrangementsUtil(dp,arr,1,0,n);
    }
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; testCase++) {
            
            int n = Integer.parseInt(br.readLine());
            
            ArrayList<ArrayList<Integer>> list = new ArrayList<>();
            for(int i=0;i<n;i++){
                String ip[] = br.readLine().split(" ");
                ArrayList<Integer> list1 = new ArrayList<>();
                for (int j = 0; j < ip.length; j++) {
                    list1.add(Integer.parseInt(ip[j]));
                }
                list.add(list1);
            }
             // Convert each ArrayList to an int array
            int[][] arr = new int[n][101];  // Create a 2D array to store the converted arrays
            
            for(int i=0;i<list.size();i++){
               for(int tShirt : list.get(i)){
                    arr[i][tShirt] = 1;
                }
            }
            System.out.println(findArrangements(arr,n));
        }
    }
}
