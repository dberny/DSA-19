import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> map = new HashMap<>();
            int x = points[i][0];
            int y = points[i][1];
//            System.out.println("" + x + " " + y);
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int a = points[j][0];
                int b = points[j][1];
                double distance = Math.sqrt((x-a)*(x-a) + (y-b)*(y-b));
//                System.out.println("distance " + distance);
                Integer count = map.get(distance);
                if (count == null) {
                    map.put(distance, 1);
                }
                else {
                    map.put(distance, count+1);
                }
            }
            for (Integer eqpoints : map.values()) {
                sum += eqpoints*(eqpoints-1);
            }
//            map.clear();
        }
        return sum;
    }
}

