import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    private static double cost(String[] words, int lo, int hi, int m) {
        if (hi <= lo){
            System.out.println("lo " + lo + " hi " + hi);
            throw new IllegalArgumentException("Hi must be higher than Lo");}
        int length = hi-lo-1; // account for spaces;
        for (int i = lo; i < hi; i++) {
            length += words[i].length();
        }
        if (length > m)
            return Double.POSITIVE_INFINITY;
        return Math.pow(m-length, 3);
    }

    public static List<Integer> justifyText(String[] w, int m) {
        int[][] numSpaces = new int[w.length+1][w.length+1];
        double[][] costLine = new double[w.length+1][w.length+1];

        int[] costDP = new int[w.length];
        int[] wordIndex = new int[w.length];

//        LinkedList<Integer> wordIndex = new LinkedList<>();

        for (int i = 0; i < w.length; i++) {
//            numSpaces[i][i] = m - w[i-1].length();
//
//            for (int j = i+1; j < w.length+1; j++) {
//                numSpaces[i][j] = numSpaces[i][j-1] - w[j-1].length() - 1;
//            }
            for (int j = 0; j < w.length; j++) {
                if (i < j) {
                    costLine[i][j] = cost(w, i, j, m);
                }
            }
        }
        printdp(costLine);

        costDP[0] = 0;
        for (int j = 1; j < w.length; j++) {
            costDP[j] = Integer.MAX_VALUE;
            for (int i = 1; i < w.length+1; i++) {
                if (costDP[i-1] < Integer.MAX_VALUE && costLine[i][j] < Integer.MAX_VALUE && costDP[i-1]+costLine[i][j] < costDP[j]) {
                    System.out.println(costDP[i-1] + (int)(costLine[i][j]));
                    costDP[j] = costDP[i-1] + (int)(costLine[i][j]);
                    wordIndex[j]=i;
                }
            }
        }
        for (int cost : costDP) {
            System.out.print(cost + " ");
        }

        LinkedList output = new LinkedList();
//        printdp(cost);
        buildList(costDP, output, costDP.length-1);
        return output;
//        return null;
    }

    public static void buildList(int[] nums, LinkedList<Integer> output, int index) {
//        if (index == 0) {
//            output.addFirst(0);
//            return output;
//        }
//        int last = nums[index];
        while (index > 0) {
            output.addFirst(nums[index]);
            index = nums[index];
        }
    }


    public static void printdp(double[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j< nums[0].length; j++) {
                System.out.print(nums[i][j] + "   ");
                if (nums[i][j] < 100) {
                    System.out.print("  ");
                }
                else if (nums[i][j] < 1000) {
                    System.out.print("");
                }
                else if (nums[i][j] < 10000) {
                    System.out.print(" ");
                }

            }
            System.out.println();
        }
    }
}