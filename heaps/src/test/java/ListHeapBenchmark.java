import by.example.ListHeap;

import java.util.Random;

public class ListHeapBenchmark {
    public static void main(String[] args) {
        int size = 1023;
        int bound = 100_000;
        int accuracy = 10_000;
        int[] increments = {10, 20, 40, 80, 100};
        System.out.println("Push number\t\tDepth");
        for (int increment : increments) {
            int maxDepth = 0;
            Random random = new Random();
            ListHeap listHeap = new ListHeap();
            for (int t = 0; t < size; t++) {
                int item = random.nextInt(bound);
                listHeap.enqueue(item);
            }
            for (int j = 0; j < accuracy; j++) {
                    int depth = listHeap.push(increment);
                    if (depth > maxDepth) {
                        maxDepth = depth;
                    }
            }
            System.out.printf("%d \t\t\t %d\n", increment, maxDepth);
        }
    }
}
