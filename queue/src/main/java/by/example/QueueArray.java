package by.example;

public class QueueArray {
    int first, last;
    Integer[] queue;
    int size;

    public QueueArray() {
        size = 1;
        queue = new Integer[size];
        first = 0;
        last = 0;
    }

    public void add(Integer item) {
        queue[last] = item;
        last = (last + 1) % size;
        if (last == first) {
            Integer[] copy = new Integer[size * 2];
            int c = 0;
            for (int i = first; i < size; i++) {
                copy[c++] = queue[i];
            }
            for (int i = 0; i < last; i++) {
                copy[c++] = queue[i];
            }
            queue = copy;
            first = 0;
            last = c;
            size = size * 2;
        }
    }

    public Integer remove() {
        if (first == last) {
            return null;
        }
        Integer item = queue[first];
        queue[first] = null;
        first = (first + 1) % size;
        return item;
    }

    public boolean isEmpty() {
        return first == last;
    }
}