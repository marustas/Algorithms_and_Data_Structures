package by.example;

public class ArrayHeap {
    // magic needs to be the last element after the filled spot
    int[] heap;
    int root = 0;
    int left, right;
    int k;

    public ArrayHeap(int size) {
        heap = new int[size];
        root = heap[0];
        k = heap.length - 1;
    }

    public void bubble(int item) {
        if(heap[root] == 0){
            heap[root] = item;
        }else{
            int parent = item % 2 == 0 ? (item - 2) / 2 : (item - 1) / 2;
            heap[k] = item;
            if (heap[k] < heap[parent]) {
                int temp = heap[parent];
                heap[parent] = heap[k];
                heap[k] = temp;
                bubble(heap[k]);
            }
        }
    }

    public int sink() {
        left = root * 2 + 1;
        right = root * 2 + 2;
        heap[root] = heap[k];
        int swapIndex = heap[left] > heap[right] ? right : left;
        if (heap[root] > heap[swapIndex]) {
            int temp = heap[root];
            heap[root] = heap[swapIndex];
            heap[swapIndex] = temp;
        }
        k = k - 1;
        return heap[root];
    }

    public static void main(String[] args){
        ArrayHeap arrayHeap = new ArrayHeap(5);
        arrayHeap.bubble(5);
        arrayHeap.bubble(8);
        arrayHeap.bubble(1);
        arrayHeap.bubble(3);
        arrayHeap.bubble(4);
        System.out.println(arrayHeap.sink());
        System.out.println(arrayHeap.sink());
    }
}
