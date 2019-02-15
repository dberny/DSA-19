import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(n logn)
     * Worst-case runtime: O(n logn)
     * Average-case runtime: O (n logn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        if (array.length <= 1) {
            return array;
        }
        int[] left = Arrays.copyOfRange(array, 0, array.length/2);
        int[] right = Arrays.copyOfRange(array, array.length/2, array.length);
        left = sort(left);
        right = sort(right);
        int[] merged = merge(left, right);
        return merged;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        // TODO
        int i = 0;
        int j = 0;
        int[] merged = new int[a.length + b.length];
        int pos = 0;
        while (i<a.length && j<b.length) {
            if (a[i] < b[j]) {
                merged[pos] = a[i];
                i++;
                pos++;
            }
            else {
                merged[pos] = b[j];
                j++;
                pos++;
            }
        }
        if (i == a.length) {
            for (j = j; j < b.length; j++) {
                merged[pos] = b[j];
                pos++;
            }
        }
        else {
            for (i = i; i < a.length; i++) {
                merged[pos] = a[i];
                pos++;
            }
        }
        return merged;
    }

}
