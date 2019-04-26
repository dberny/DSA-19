import java.util.HashMap;

public class DiceRollSum {

    // Runtime: O(6*n)
    // Space: O(n)

    public static int diceRollSum(int N) {
        HashMap<Integer, Integer> sols = new HashMap<>();
        int sum = helper(N, sols);
        return sum;
    }

    public static int helper(int N, HashMap<Integer, Integer> sols) {
        if (N < 0) {
            return 0;
        }
        if (N == 0) {
            return 1;
        }
        if (sols.containsKey(N)) {
            return sols.get(N);
        }
        int newVal = helper(N-1, sols) + helper(N-2, sols) + helper(N-3, sols) + helper(N-4, sols) + helper(N-5, sols) + helper(N-6, sols);
        sols.put(N, newVal);
        return newVal;
    }
}
