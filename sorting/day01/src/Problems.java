import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        // TODO
        int med1 = Integer.MIN_VALUE;
        int med2 = Integer.MIN_VALUE;
        PriorityQueue<Integer> bigHalf = minPQ();
        PriorityQueue<Integer> smallHalf = maxPQ();
        for (int i = 0; i < inputStream.length; i++) {
            if (i == 0) {
                bigHalf.add(inputStream[i]);
            }
            else if (inputStream[i] > bigHalf.peek()) {
                bigHalf.add(inputStream[i]);
                if (bigHalf.size() > smallHalf.size()) {
                    smallHalf.add(bigHalf.poll());
                }
            }
            else {
                smallHalf.add(inputStream[i]);
                if (smallHalf.size() > bigHalf.size()) {
                    bigHalf.add(smallHalf.poll());
                }
            }
            if (i%2 == 0) {
                if (bigHalf.size() > smallHalf.size()) {
                    runningMedian[i] = (bigHalf.peek());
                }
                else {
                    runningMedian[i] = smallHalf.peek();
                }
            }
            else {
                runningMedian[i] = (bigHalf.peek()+smallHalf.peek())/2.0;
            }
        }
        return runningMedian;
    }

}
