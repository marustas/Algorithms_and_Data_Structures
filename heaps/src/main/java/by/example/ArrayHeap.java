package by.example;

public class ArrayHeap {
    int element = 5;
    int parent = (element - 1) /2;
    // magic needs to be the last element after the filled spot
    int[] heap;

    public ArrayHeap() {
        heap = new int[0];
    }
}
