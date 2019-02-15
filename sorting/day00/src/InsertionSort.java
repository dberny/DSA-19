
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int move = array[i];
            int j = i-1;
            while (j>=0 && (move < array[j])) {
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = move;
        }
        return array;
    }
}
