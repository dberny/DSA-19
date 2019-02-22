import java.util.Arrays;
import java.util.HashMap;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            HashMap<Integer,Integer> map = new HashMap<>();

            for (int j = i+1; j < arr.length; j++) {

                int num =  (sum - arr[i]-arr[j]);
                if (map.containsKey(num)) {
                    count++;
                }
                else {
                    map.put(arr[j], 0);
                }
            }
        }

        return count;
    }
}
