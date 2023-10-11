package by.example;

import java.util.Random;

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

    public int remove() {
        int removedElement = heap[root];
        heap[root] = heap[size - 1];
        size--;
        sink(root);
        heap[size] = 0;
        return removedElement;
    }

    private void sink(int index) {
        int currentIndex = index;
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
    }

    public void push(int increment) {
        heap[root] += increment;
        sink(root);
    }

    public void swap(int a, int b) {
        int temp1 = heap[a];
        heap[a] = heap[b];
        heap[b] = temp1;
    }

    public static void main(String[] args) {
        ArrayHeap arrayHeap = new ArrayHeap(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            arrayHeap.add(random.nextInt(100));
        }
        int i = 0;
        while (i < 10) {
            System.out.println(arrayHeap.remove());
            i++;
        }

    }
}
