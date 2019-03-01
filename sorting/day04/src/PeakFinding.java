public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    static int findPeak(int l, int r, int[] nums) {
        if (l > r) {
            return -1;
        }
        int mid = (l+r)/2;
        int peak = peakOneD(mid, nums);
        if (peak == 0) {
            return mid;
        }
        if (peak == -1) {
            return findPeak(l, mid, nums);
        }
        else {
            return (findPeak(mid+1, r, nums));
        }
    }

    public static int findOneDPeak(int[] nums) {
        return findPeak(0, nums.length-1, nums);
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int answer[] = new int[2];
        int mid = nums.length/2;
        if ((peakX(nums[0].length-1, nums.length-1, nums) == 0) && peakY(nums[0].length-1, nums.length-1, nums) == 0) {
            answer[0] = nums.length-1;
            answer[1] = nums[0].length-1;
            return answer;
        }
        if (mid == 0 || mid == nums.length-1) {
            answer[0] = mid;
            answer[1] = maxYIndex(mid, 0, nums.length-1, nums);
        }
        else {
            answer[0] = mid;
            answer[1] = findOneDPeak(nums[mid]);
        }
        return answer;
    }


}
