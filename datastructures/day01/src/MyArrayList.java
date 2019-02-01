public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        if (size == elems.length) {
            Cow[] newArray;
            newArray = new Cow[size*2];
            System.arraycopy(elems, 0, newArray, 0, size);
            elems = newArray;
        }
        elems[size] = c;
        size++;
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        return elems[index];
    }

    // TODO: Runtime: O(n)
    public Cow remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("this cow doesn't exist!");
        }
        Cow temp = elems[index];
        for (int i = index; i < elems.length-1; i++) {
            elems[i] = elems[i+1];
        }
        size--;
        if (size*4 < elems.length) {
            Cow[] newArray;
            newArray = new Cow[size*2];
            System.arraycopy(elems, 0, newArray, 0, size);
            elems = newArray;
        }
        return temp;
    }

    // TODO: Runtime: O(n)
    public void add(int index, Cow c) {
        if (index > size) {
            throw new IndexOutOfBoundsException("no stop don't do that");
        }
        if (size == elems.length) {
            Cow[] newArray;
            newArray = new Cow[size*2];
            System.arraycopy(elems, 0, newArray, 0, size);
            elems = newArray;
        }
        for (int i = size; i > index; i--) {
            elems[i] = elems[i-1];
        }
        elems[index] = c;
        size++;
    }
}