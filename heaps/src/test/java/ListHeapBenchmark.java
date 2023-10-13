import by.example.ListHeap;

import java.util.Random;

public class ListHeapBenchmark {
    public static void main(String[] args) {
        int size = 1023;
        int bound = 100_000;
        int accuracy = 10_000;
        int[] operations = {100, 200, 400, 800, 1600};
        System.out.println("Attempt\t\tPush operation\t\tEnqueue+Dequeue operation\t\tRatio\t\tDepth");
        int attempt = 1;
        for (int i = 0; i < operations.length; i++) {
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;
            int maxDepth = 0;
            for (int j = 0; j < accuracy; j++) {
                min1 = Double.POSITIVE_INFINITY;
                min2 = Double.POSITIVE_INFINITY;
                maxDepth = 0;
                Random random = new Random();

                ListHeap listHeap1 = new ListHeap();
                for (int t = 0; t < size; t++) {
                    int item = random.nextInt(bound);
                    listHeap1.enqueue(item);
                }

                ListHeap listHeap2 = new ListHeap();
                for (int t = 0; t < size; t++) {
                    int item = random.nextInt(100_000);
                    listHeap2.enqueue(item);
                }
                for (int operation : operations) {
                    double start1 = System.nanoTime();
                    for (int o = 0; o < operation; o++) {
                        int depth = listHeap1.push(random.nextInt(10, 100));
                        if (depth > maxDepth) {
                            maxDepth = depth;
                        }
                    }
                    double time1 = System.nanoTime() - start1;
                    if (time1 < min1) {
                        min1 = time1;
                    }

                    double start2 = System.nanoTime();
                    for (int o = 0; o < operation; o++) {
                        int removedItem = listHeap2.dequeue();
                        removedItem += random.nextInt(10, 100);
                        listHeap2.enqueue(removedItem);
                    }
                    double time2 = System.nanoTime() - start2;
                    if (time2 < min2) {
                        min2 = time2;
                    }
                }
            }
            System.out.printf("#%d \t\t\t %.2f \t\t\t\t\t %.2f \t\t\t\t\t %.2f\t\t%d\n", attempt, min1 / 1000, min2 / 1000, min1 / min2, maxDepth);
            attempt++;
        }
    }
}

