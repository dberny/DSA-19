package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxl;
    private int max;

    public MyStack() {
        ll = new LinkedList<>();
        maxl = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        ll.addFirst(e);
        if (ll.isEmpty()) {
            max = e;
            maxl.addFirst(e);
        }
        else if (e >= max) {
            maxl.addFirst(e);
            max = e;
        }
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if (pop == max) {
            maxl.removeFirst();
            if (!maxl.isEmpty()) {
                max = maxl.peek();
            }
            else {
                max = Integer.MIN_VALUE;
            }
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return max;
    }
}
