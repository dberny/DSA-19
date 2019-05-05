import java.util.*;

public class SplitCoins {

    public static int splitCoins(int[] coins) {
        int total = 0;
        for (int num : coins) {
            total += num;
        }

//        HashMap<Integer,Integer> used = new HashMap<>();
        PriorityQueue<Integer> used = new PriorityQueue<>();
        HashMap<PriorityQueue<Integer>, Integer> maps = new HashMap<>();

        return helper(coins, 0, total, 0, used, maps);
    }

    public static int helper(int[]coins, int index, int total, int group, PriorityQueue<Integer> used, HashMap<PriorityQueue<Integer>, Integer> maps) {
//        if (coins.length == 0) {
//            return Math.abs(Math.min(total-group, group));
//        }
        if (maps.containsKey(used)) {
//            System.out.println(used);

            return maps.get(used);

        }

        if (index == coins.length-1) {
            int min = maps.getOrDefault(used, Integer.MAX_VALUE);
            maps.put(used, Math.min(min, Math.abs((total-group)- group)));
            return Math.abs((total-group)- group);
        }

        int add = coins[index];
        PriorityQueue<Integer> newused = (PriorityQueue<Integer>) used;
//        System.out.println(add);
        newused.add(add);
//        System.out.println(newused);
        return Math.min((helper(coins, index+1, total, group+add, newused, maps)), helper(coins, index+1, total, group, used, maps));
    }

    public static void printdp(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j< nums[0].length; j++) {
                System.out.print(nums[i][j] + " ");
                if (nums[i][j] < 10) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
