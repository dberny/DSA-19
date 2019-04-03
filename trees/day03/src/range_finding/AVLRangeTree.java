package range_finding;

import java.util.LinkedList;
import java.util.List;

public class AVLRangeTree extends BinarySearchTree<Integer> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> delete(RangeNode<Integer> n, Integer key) {
        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.leftChildren = countChildren(n.leftChild) +1;
            n.rightChildren = countChildren(n.rightChild) + 1;
            return balance(n);
        }

        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    RangeNode<Integer> insert(RangeNode<Integer> n, Integer key) {
        n = super.insert(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.leftChildren = countChildren(n.leftChild) +1;
            n.rightChildren = countChildren(n.rightChild) + 1;
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    RangeNode<Integer> deleteMin(RangeNode<Integer> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            n.leftChildren = countChildren(n.leftChild) +1;
            n.rightChildren = countChildren(n.rightChild) + 1;
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(RangeNode x) {
        if (x == null) return -1;
        return x.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree.
    RangeNode<Integer> balance(RangeNode<Integer> x) {
        if (balanceFactor(x) > 1) {
            if (balanceFactor(x.rightChild) < 0) {
                x.rightChild = rotateRight(x.rightChild);
            }
            x = rotateLeft(x);
        } else if (balanceFactor(x) < -1) {
            if (balanceFactor(x.leftChild) > 0) {
                x.leftChild = rotateLeft(x.leftChild);
            }
            x = rotateRight(x);
        }
        return x;
    }

    public int countChildren(RangeNode node) {
        if (node == null) {
            return -1;
        }
        return node.leftChildren + node.rightChildren;
    }
    // Return all keys that are between [lo, hi] (inclusive).
    // TODO: runtime = O(?)
    public List<Integer> rangeIndex(int lo, int hi) {
        // TODO
//        RangeNode min = findStart(root, lo);
//        System.out.println(min.key);
        List<Integer> l = new LinkedList<>();
        inOrderRange(root, l, lo, hi);
        System.out.println(l);
        return l;
    }
    public void inOrderRange(RangeNode node, List list, int lo, int hi) {
        if (node != null) {
            inOrderRange(node.leftChild, list, lo, hi);
            System.out.print(node.key);
            if (node.key.compareTo(hi) <= 0 && node.key.compareTo(lo) >=0) {
                list.add(node.key);
            }
//            else {
//                return;
//            }
            inOrderRange(node.rightChild, list, lo, hi);
        }
    }

    public RangeNode findStart(RangeNode node, int lo) {
        if (node.leftChild == null) {
            return node;
        }
        if (node.key.compareTo(lo) > 0) {
            return findStart(node.leftChild, lo);
        }
        return node;
    }

    // return the number of keys between [lo, hi], inclusive
    // TODO: runtime = O(?)
    public int rangeCount(int lo, int hi) {
        // TODO
        int high = greaterThan(hi, root);
//        System.out.println(high);
        int low = greaterThan(lo-1, root);
//        System.out.println(low);
        int between = high-low;
        return between;
    }

    // return the num of nodes smaller than num
    public int greaterThan(int num, RangeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.key.compareTo(num) <= 0) {
            System.out.println(node.leftChildren);
//            System.out.println(node.key);
//            System.out.println(node.leftChildren + 1 + greaterThan(num, node.rightChild));
            return node.leftChildren + 1 + greaterThan(num, node.rightChild);
        }
        return greaterThan(num, node.leftChild);
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(RangeNode x) {
        return height(x.rightChild) - height(x.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateRight(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.leftChildren = countChildren(x.leftChild)+1;
        x.rightChildren = countChildren(x.rightChild)+1;
        y.leftChildren = countChildren(y.leftChild)+1;
        y.rightChildren = countChildren(y.rightChild)+1;
        return y;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private RangeNode<Integer> rotateLeft(RangeNode<Integer> x) {
        RangeNode<Integer> y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;
        x.height = 1 + Math.max(height(x.leftChild), height(x.rightChild));
        y.height = 1 + Math.max(height(y.leftChild), height(y.rightChild));
        x.leftChildren = countChildren(x.leftChild)+1;
        x.rightChildren = countChildren(x.rightChild)+1;
        y.leftChildren = countChildren(y.leftChild)+1;
        y.rightChildren = countChildren(y.rightChild)+1;
        return y;
    }
}
