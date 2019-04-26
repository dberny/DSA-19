import java.util.*;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        Arrays.sort(occupied);
//        System.out.print("occupied: " + occupied[0]);
        PriorityQueue<Integer> gaps = new PriorityQueue<>(10, Collections.reverseOrder());
        for (int i = 1; i < occupied.length; i++) {
//            System.out.print(" , " + occupied[i]);
            int gap = occupied[i] - occupied[i-1] - 1;
            if (gap > 0) {
                gaps.add(gap);
//                System.out.println(gap);
            }
        }

//        System.out.println(gaps);

        int total = occupied[occupied.length-1] - (occupied[0]-1);
        while (M > 1 && !gaps.isEmpty()) {
            int remove = gaps.poll();
//            System.out.println("gap: " + remove);
            total -= remove;
            M--;
        }
//        System.out.println("total: " + total);
        return total;
    }
}