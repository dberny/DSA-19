public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return helper(n, isBadVersion, 0, n);
    }

    public static long helper(long n, IsFailingVersion version, long low, long hi) {
        long mid = (low+hi)/2;
        if (low >= hi) {
            return low;
        }
        if (version.isFailingVersion(mid)) {
            return helper(n, version, low, mid);
        }
        else {
            return helper(n, version, mid+1, hi);
        }
    }
}
