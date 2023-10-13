import by.example.ArrayHeap;

import java.util.Random;

public class ArrayHeapBenchmark {
    private static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(10, bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800, 1600};
        int bound = 100_000;
        int accuracy = 10_000;
        int operations = 1000;
        System.out.println("Size\t\tPush operation\t\tEnqueue+Dequeue operation\t\tRatio\t\tDepth");
        for (int size : sizes) {
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;
            int maxDepth = 0;
            for (int j = 0; j < accuracy; j++) {
                min1 = Double.POSITIVE_INFINITY;
                min2 = Double.POSITIVE_INFINITY;
                maxDepth = 0;
                Random random = new Random();

                int[] increments = createArray(operations, 100);
                ArrayHeap arrayHeap1 = new ArrayHeap(size);
                ArrayHeap arrayHeap2 = new ArrayHeap(size);
                for (int t = 0; t < size; t++) {
                    int item = random.nextInt(bound);
                    arrayHeap1.add(item);
                    arrayHeap2.add(item);
                }

                double start1 = System.nanoTime();
                for (int o = 0; o < operations; o++) {
                    int depth = arrayHeap1.push(increments[o]);
                    if (depth > maxDepth) {
                        maxDepth = depth;
                    }
                }
                double time1 = System.nanoTime() - start1;
                if (time1 < min1) {
                    min1 = time1;
                }

                double start2 = System.nanoTime();
                for (int o = 0; o < operations; o++) {
                    int removedItem = arrayHeap2.remove();
                    removedItem += increments[o];
                    arrayHeap2.add(removedItem);
                }
                double time2 = System.nanoTime() - start2;
                if (time2 < min2) {
                    min2 = time2;
                }
            }
            System.out.printf("%d \t\t\t %.2f \t\t\t\t\t %.2f \t\t\t\t\t %.2f\t\t%d\n", size, min1 / 1000, min2 / 1000, min1 / min2, maxDepth);
        }
    }
}
