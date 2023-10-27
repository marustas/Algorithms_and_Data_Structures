package by.example;

public class Queue {
    private int last;
    Path[] heap;

    public Queue(int capacity) {
        last = 0;
        heap = new Path[capacity];
    }

    private boolean isEmpty() {
        return last == 0;
    }

    public void add(Path path) {
        heap[last] = path;
        path.index = last;
        bubble(last);
        last++;
    }

    private void bubble(Integer index) {
        int currentIndex = index;
        int parentIndex = getParentIndex(currentIndex);
        while (currentIndex > 0 && heap[currentIndex].dist < heap[parentIndex].dist) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    private int getParentIndex(int t) {
        return t % 2 == 0 ? (t - 2) / 2 : (t - 1) / 2;
    }

    public Path remove() {
        if (last == 0)
            return null;
        Path next = heap[0];
        heap[0] = heap[last - 1];
        heap[0].index = 0;
        heap[last - 1] = null;
        sink(last);
        return next;
    }

    private void sink(int index) {
        int currentIndex = index;
        int left, right, smallest;

        while (true) {
            left = currentIndex * 2 + 1;
            right = currentIndex * 2 + 2;
            smallest = currentIndex;

            smallest = (left < last && heap[left].dist < heap[smallest].dist) ? left : smallest;
            smallest = (right < last && heap[right].dist < heap[smallest].dist) ? right : smallest;

            if (smallest != currentIndex) {
                swap(currentIndex, smallest);
                currentIndex = smallest;
            } else {
                break;
            }
        }
    }

    private void swap(int a, int b) {
        Path temp = heap[a];
        heap[b].index = a;
        heap[a] = heap[b];
        temp.index = b;
        heap[b] = temp;
    }
}

