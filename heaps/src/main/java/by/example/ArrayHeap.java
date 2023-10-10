package by.example;

public class ArrayHeap {
    // magic needs to be the last element after the filled spot
    int[] heap;
    int root;

    public ArrayHeap(int size) {
        heap = new int[size];
        root = heap[0];
    }

    public void bubble(int item) {
        int parent = item % 2 == 0 ? (item - 2) / 2 : (item - 1) / 2;
        if (item < heap[parent]) {
            int temp = heap[parent];
            heap[parent] = item;
            item = temp;
            bubble(item);
        }
    }
}
