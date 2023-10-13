import by.example.ArrayHeap;

import java.util.Random;

public class ArrayHeapBenchmark {
    public static void main(String[] args) {
        int size = 1023;
        int bound = 100_000;
        int accuracy = 100_000;
        int[] increments = {10, 20, 40, 80, 100};
        System.out.println("Push number\t\tDepth");
        for (int increment : increments) {
            int maxDepth = 0;
            Random random = new Random();
            ArrayHeap arrayHeap = new ArrayHeap(1023);
            for (int t = 0; t < size; t++) {
                int item = random.nextInt(bound);
                arrayHeap.add(item);
            }
            for (int j = 0; j < accuracy; j++) {
                int depth = arrayHeap.push(increment);
                if (depth > maxDepth) {
                    maxDepth = depth;
                }
            }
            System.out.printf("%d \t\t\t %d\n", increment, maxDepth);
        }
    }
}