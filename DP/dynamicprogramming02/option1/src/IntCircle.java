import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IntCircle{
    public static int maxValue(int[] circle) {
        int[][] dp = new int[circle.length+2][circle.length+2];
        for (int num : circle) {
            System.out.print(num + " ");
        }
        System.out.println();

        for (int subSize = 1; subSize < circle.length; subSize++) {
            for (int left = 1; left < circle.length-subSize; left++) {
//                for (int right = 1; right < nums.length-1; right++) {
                int right = left + subSize - 1;
                if (left > right) {
                    continue;
                }
                if (subSize == 1) {
                    dp[left][right] = circle[left];
                    System.out.println(left + " " + circle[left]);
                }

                for (int mid = left; mid <= right; mid++) {
//                    System.out.println(dp[i][mid-1] + nums[i-1] * nums[mid] * nums[j+1] + dp[mid+1][j]);
                    dp[left][right] = Math.max(dp[left][right], dp[left][mid-1] + circle[left-1] * circle[mid] * circle[right+1] + dp[mid+1][right]);
                }
//                }
            }
        }
        printdp(dp);
//        return dp[1][B.length];
        return 0;
    }

    public static int memo(int[] nums, HashMap<Integer[], Integer> dict) {
        if (dict.containsKey(nums)) {
            return dict.get(nums);
        }
        if (nums.length == 3) {
            int prod = 1;
            for (int num : nums) {
                prod *= num;
            }
        }
        ArrayList<Integer> outs = new ArrayList<>();
        int[] pops = new int[nums.length-2];
        for (int i = 1; i < nums.length-1; i++) {
//            int[] newarray = pop(nums, i);
            int pop = popNum(nums, i);
            pops[i-1] = pop;
        }
        int indexMax = maxIndex(pops)+1;
        int[] popped = pop(nums, indexMax);
        int val = nums[indexMax] * memo(popped, dict);
        dict.put(nums, val);
        return val;
    }

    public static int maxIndex(int[] nums) {
        int num = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > num) {
                num = nums[i];
                index = i;
            }
        }
        return index;
    }

    public static int popNum(int[] nums, int index) {
        int prod = 1;
        for (int i = index-1; i <= index+1; i++) {
            prod *= nums[i];
        }
        return prod;
    }

    public static int[] pop(int[] nums, int index) {
        int[] copy = new int[nums.length-3];
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == index-1 || i == index || i == index+1) {
                continue;
            }
            copy[ind] = nums[i];
            ind++;
        }
        return copy;
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