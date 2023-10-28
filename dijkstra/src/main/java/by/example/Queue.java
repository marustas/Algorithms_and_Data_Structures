package by.example;

public class Queue {
    private int last;
    Path[] heap;

    public Queue(int capacity) {
        last = 0;
        heap = new Path[capacity];
    }

    public boolean isEmpty() {
        return last == 0;
    }

    public void add(Path path) {
        heap[last] = path;
        path.index = last;
        bubble(last);
        last++;
    }

    public Path remove() {
        if (last == 0)
            return null;
        Path next = heap[0];
        heap[0] = heap[(last - 1)];
        heap[0].index = 0;
        heap[(last - 1)] = null;
        last--;
        sink();
        return next;
    }

    private int getParentIndex(int t) {
        return t % 2 == 0 ? (t - 2) / 2 : (t - 1) / 2;
    }

    public void bubble(int index) {
        int currentIndex = index;
        int parentIndex = getParentIndex(index);
        while (currentIndex > 0 && heap[currentIndex].dist < heap[parentIndex].dist) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    private void sink() {
        int currentIndex = 0;
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

    private void swap(int i, int j) {
        Path k = heap[i];
        heap[j].index = i;
        heap[i] = heap[j];
        k.index = j;
        heap[j] = k;
    }
}

