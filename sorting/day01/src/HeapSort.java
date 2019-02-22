public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if (leftChild(i) >= size) {
            return;
        }
        int left = leftChild(i);
        if (rightChild(i) >= size) {
            if (heap[left] > heap[i]) {
                swap(heap, leftChild(i), i);
                sink(left);
            }
            return;
        }
        int right = rightChild(i);
        if (heap[left] > heap[right]) {
            if (heap[i] < heap[left]) {
                swap(heap, leftChild(i), i);
                sink(left);
            }
        }
        else {
            if (heap[i] < heap[right]) {
                swap(heap, rightChild(i), i);
                sink(right);
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;
        for (int i=this.size/2-1; i>=0; i--) {
            sink(i);
        }
    }

    /**
     * Best-case runtime: O (nlogn)
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        for (int i=size-1; i>0; i--) {
            swap(array, 0, size-1);
            size--;
            sink(0);
        }
        return heap;
    }
}
