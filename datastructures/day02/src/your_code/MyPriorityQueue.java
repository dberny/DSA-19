package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private LinkedList<Integer> ll;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
    }

    public void enqueue(int item) {
        if (ll.isEmpty()) {
            ll.add(item);
        }
        else {
            LinkedList<Integer> temp;
            temp = new LinkedList<>();
            boolean added = false;
            do {
                int top = ll.removeFirst();

                if (item > top && !added) {
                    temp.addLast(item);
                    temp.addLast(top);
                    added = true;
                }
                else {
                    temp.addLast(top);
                }
            } while (!ll.isEmpty());

            ll = temp;
        }
    }


    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
//        System.out.println("NEW");
//        for(int p=0; p < ll.size(); p++)
//        {
//            System.out.println("Element at index "+p+": "+ll.get(p));
//        }
        return ll.removeFirst();
    }

}