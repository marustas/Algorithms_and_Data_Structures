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
        root = heap[0];
    }

    public void add(int item) {
        heap[size] = item;
        int currentIndex = size;
        int parentIndex = currentIndex % 2 == 0 ? (currentIndex - 2) / 2 : (currentIndex - 1) / 2;
        size++;
        while (currentIndex > 0 && heap[currentIndex] < heap[parentIndex]) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = currentIndex % 2 == 0 ? (currentIndex - 2) / 2 : (currentIndex - 1) / 2;
        }
    }

    public int sink() {
        return heap[root];
    }

    public void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    public static void main(String[] args) {
        ArrayHeap arrayHeap = new ArrayHeap(6);
        arrayHeap.add(5);
        arrayHeap.add(8);
        arrayHeap.add(1);
        arrayHeap.add(4);
        arrayHeap.add(3);
        System.out.println(arrayHeap.sink());
    }
}
