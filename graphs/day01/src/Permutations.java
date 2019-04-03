import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {

        List<Integer> unused = new LinkedList<>();
        for (int num : A) {
            unused.add(num);
        }
        List<List<Integer>> permutations = new LinkedList<>();
        List<Integer> current = new LinkedList<>();

        permutationsHelper(current, unused, permutations);
//        System.out.println(permutations);
        return permutations;
    }

    public static void permutationsHelper(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations) {
        if (unused.isEmpty()) {
//            System.out.println(current);
            List copy = List.copyOf(current);
            permutations.add(copy);
        }
        for (int i = 0; i < unused.size(); i++) {
            int num = unused.remove(i);
            current.add(num);
            permutationsHelper(current, unused, permutations);
            current.remove(current.size()-1);
            unused.add(i, num);
        }
    }
}
