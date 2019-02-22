import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
//import java.util.Map;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // TODO
        // split into array by spaces: O(n)
        // make histogram of counts O(n)
        // priority queue of keys based on count values O(log k)
        // build string based on sorted values
        String[] array = s.split(" ");
        HashMap<String, Integer> map = new HashMap<>();
        PriorityQueue<String> counts = new PriorityQueue<>(11, new Compare(map));
        String result = "";
        for (int i = 0; i < array.length; i++) {
            map.put(array[i], (map.getOrDefault(array[i], 0))+1);
        }
        for (String key : map.keySet()) {
            counts.add(key);
        }
        while (!counts.isEmpty()) {
            String word = counts.poll();
            int count = map.get(word);
            while (count > 0) {
                result = result + word + " ";
                count--;
            }
        }
        return result.substring(0, result.length()-1);
    }

}

class Compare implements Comparator<String> {
    HashMap<String, Integer> map;

    public Compare(HashMap<String, Integer> map) {
        this.map = map;
    }

    public int compare(String val1, String val2) {
        if (map.get(val1) >= map.get(val2)) {
            return 1;
        }
        else {
            return -1;
        }
    }
}