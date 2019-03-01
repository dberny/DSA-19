public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        // TODO
        int max = 0;
        for (int num : A) {
            if (num > max) {
                max = num;
            }
        }
        int[] counts = new int[max+1];
        for (int num : A) {
            counts[num]++;
        }
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                A[index] = i;
                counts[i]--;
                index++;
            }
        }
    }

}
