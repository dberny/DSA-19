package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;
    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node node = new Node(c);
        if (isEmpty()) {
            head = node;
        }
        else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        size++;
    }

    public void addFirst(Chicken c) {
        Node node = new Node(c);
        if (isEmpty()) {
            tail = node;
        }
        else {
            head.prev = node;
            node.next = head;
        }
        head = node;
        size++;
    }

    public Chicken get(int index) {
        Node current = head;
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("that's not a chicken");
        }
        else {
            int count = 0;
            while (count < index) {
                current = current.next;
                count++;
            }
        }
        return current.val;
    }

    public Chicken remove(int index) {
        Node current = head;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("that's not a chicken");
        }
        else {
            int count = 0;
            while (count < index) {
                current = current.next;
                count++;
            }
            if (current.prev != null) {
                current.prev.next = current.next;
            }
            if (current.next != null) {
                current.next.prev = current.prev;
            }
            size--;
        }
        return current.val;
    }

    public Chicken removeFirst() {
        Node first = null;
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            first = head;
            head = null;
            tail = null;
        }
        else {
            first = head;
            head = head.next;
        }
        size--;
        return first.val;
    }

    public Chicken removeLast() {
        Node last = null;
        if (isEmpty()) {
            return null;
        }
        else if (size == 1) {
            last = tail;
            head = null;
            tail = null;
        }
        else {
            last = tail;
            tail = tail.prev;
        }
        size--;
        return last.val;
    }
}