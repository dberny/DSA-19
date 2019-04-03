import java.util.*;

public class Problems {

    static void addFromList(BinarySearchTree tree, List<Integer> list) {
        if (list.isEmpty()) {
            return;
        }
        int mid = list.size()/2;
        tree.add(list.get(mid));
        addFromList(tree, list.subList(0, mid));
        addFromList(tree, list.subList(mid+1, list.size()));
    }

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        values.sort(Comparator.naturalOrder());
        BinarySearchTree tree = new BinarySearchTree();
        addFromList(tree, values);
        return tree;
//        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (!n1.equals(n2)) {
            return false;
        }
        TreeNode first = n1;
//        while (first != null) {
//            if (first.leftChild == n2.leftChild) {
//
//            }
//            if (first.leftChild == n2.rightChild) {
//
//            }
//        }
        return false;
    }
}
