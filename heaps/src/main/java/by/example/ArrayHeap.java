package by.example;

public class ArrayHeap {
    int size;
    int capacity;
    int[] heap;
    int root = 0;

    public ArrayHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        heap = new int[capacity];
    }

    public void add(int item) {
        heap[size] = item;
        int currentIndex = size;
        int parentIndex = getParentIndex(currentIndex);
        size++;
        while (currentIndex > 0 && heap[currentIndex] < heap[parentIndex]) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    private int getParentIndex(int t) {
        return t % 2 == 0 ? (t - 2) / 2 : (t - 1) / 2;
    }

    public int sink() {
        int removedElement = heap[root];
        heap[root] = heap[size - 1];
        size--;
        int currentIndex = root;
        int left, right, smallest;

        while (true) {
            left = currentIndex * 2 + 1;
            right = currentIndex * 2 + 2;
            smallest = currentIndex;

            smallest = (left < size && heap[left] < heap[smallest]) ? left : smallest;
            smallest = (right < size && heap[right] < heap[smallest]) ? right : smallest;

            if (smallest != currentIndex) {
                swap(currentIndex, smallest);
                currentIndex = smallest;
            } else {
                break;
            }
        }

        heap[size] = 0;
        return removedElement;
    }

    public void swap(int a, int b) {
        int temp1 = heap[a];
        heap[a] = heap[b];
        heap[b] = temp1;
    }

    public static void main(String[] args) {
        ArrayHeap arrayHeap = new ArrayHeap(10);
        arrayHeap.add(5);
        arrayHeap.add(6);
        arrayHeap.add(1);
        arrayHeap.add(4);
        arrayHeap.add(3);
        arrayHeap.add(7);
        arrayHeap.add(2);
        arrayHeap.add(8);
        arrayHeap.add(10);
        arrayHeap.add(9);
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
    }
}
