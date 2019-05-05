import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class BalloonPopping {

    public static int maxPoints(int[] B) {
        int[][] dp = initDP(B);

        int[] nums = new int[B.length+2];
        for (int i = 0; i < B.length; i++) {
            nums[i+1] = B[i];
        }
        nums[0] = 1;
        nums[nums.length-1] = 1;
        for (int num : nums) {
            System.out.print(num);
        }
        System.out.println();

        for (int subSize = 1; subSize < nums.length-1; subSize++) {
            for (int left = 1; left < nums.length-subSize; left++) {
//                for (int right = 1; right < nums.length-1; right++) {
                int right = left + subSize -1;
                    if (left > right) {
                        continue;
                    }
                    for (int mid = left; mid <= right; mid++) {
//                    System.out.println(dp[i][mid-1] + nums[i-1] * nums[mid] * nums[j+1] + dp[mid+1][j]);
                        dp[left][right] = Math.max(dp[left][right], dp[left][mid-1] + nums[left-1] * nums[mid] * nums[right+1] + dp[mid+1][right]);
                    }
//                }
            }
        }

        printdp(dp);
        return dp[1][B.length];
    }

    public static int[][] initDP(int[] B) {
        int[][] dp = new int[B.length + 2][B.length + 2];
        return dp;
    }

    public static int max(ArrayList<Integer> nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void printdp(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j< nums[0].length; j++) {
                System.out.print(nums[i][j] + " ");
                if (nums[i][j] < 10) {
                    System.out.print("  ");
                }
                else if (nums[i][j] < 100) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
