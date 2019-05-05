public class EditDistance {

    public static int minEditDist(String a, String b) {
        // levenstein distance
        int[][] dp = new int[a.length()+1][b.length()+1];
        for (int i= 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }

                else if (a.charAt(i-1) == b.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }

                else {
                    dp[i][j] = 1 + least(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]);
                }
            }
        }
        return dp[a.length()][b.length()];
    }

    public static int least(int a, int b, int c) {
        if (a < b && a < c) {
            return a;
        }
        else if (b < a && b < c) {
            return b;
        }
        return c;
    }

}
