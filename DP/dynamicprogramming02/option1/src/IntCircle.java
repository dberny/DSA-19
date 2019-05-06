import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class IntCircle{
    public static int maxValue(int[] circle) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int num : circle) {
            nums.add(num);
        }
        HashMap<ArrayList<Integer>, Integer> dict = new HashMap<>();
        int out = memo(nums, dict);
        return out;
    }

    public static int memo(ArrayList<Integer> nums, HashMap<ArrayList<Integer>, Integer> dict) {
//        System.out.println(nums);
        if (dict.containsKey(nums)) {
//            System.out.println("already here");
            return dict.get(nums);
        }
        if (nums.size() == 3) {
            int prod = 1;
            for (int num : nums) {
                prod *= num;
            }
            dict.put(nums, prod);
            System.out.println(prod);
            return prod;
        }

//        ArrayList<Integer> outs = new ArrayList<>();
        ArrayList<Integer> pops = new ArrayList<>();
        for (int i = 1; i < nums.size()-1; i++) {
            int pop = popNum(nums, i);
//            pops.add(pop);
            ArrayList<Integer> popped = pop(nums, i);
            int total = pop + memo(popped, dict);
            pops.add(total);
            dict.put(nums, total);
//            return dict.get(nums);
        }
//        System.out.println(pops);

        int indexMax = maxIndex(pops);
//        System.out.println("Maxindex " + indexMax);
//        ArrayList<Integer> popped = pop(nums, indexMax+1);
        int val = pops.get(indexMax) + memo(popped, dict);
        dict.put(popped, val);
        return val;
    }

    public static int maxIndex(ArrayList<Integer> nums) {
        int num = Integer.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > num) {
                num = nums.get(i);
                index = i;
            }
        }
        return index;
    }

    public static int popNum(ArrayList<Integer> nums, int index) {
        int prod = 1;
        for (int i = index-1; i <= index+1; i++) {
            prod *= nums.get(i);
        }
        return prod;
    }

    public static ArrayList<Integer> pop(ArrayList<Integer> nums, int index) {
//        int[] copy = new int[nums.size()-3];
        ArrayList<Integer> copy = new ArrayList<>();
        int ind = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (i == index-1 || i == index || i == index+1) {
                continue;
            }
            copy.add(nums.get(i));
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