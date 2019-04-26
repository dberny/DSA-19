import java.util.ArrayList;
import java.util.HashMap;

public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(n)
    public static int LIS(int[] A) {
//        HashMap<Integer, ArrayList<Integer>> series = new HashMap<>();
        if (A.length == 0) {
            return 0;
        }
        int[] dp = new int[A.length];
        dp[0] = 1;
        int longest = 0;

        for (int i = 1; i < A.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            longest = Math.max(longest, dp[i]);
        }


        return longest;
    }
//
//    public int seq(int[] A, int len, int max) {
//        if (A.length == 1) {
//            return 1;
//        }
//
//
//    }
//
//
//    public static int helper(int[] A, HashMap<Integer, ArrayList<Integer>> series) {
//
//
//
//        if (A.length == 0) {
//            return 0;
//        }
//        for (int min : series.keySet()) {
//            if (A[0] > min) {
//                series.
//            }
//        }
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(A[0]);
//        series.put(A[0], list);
//
//
//
//        return 0;
//    }



}