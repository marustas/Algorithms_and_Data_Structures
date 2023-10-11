import by.example.ListHeap;

import java.util.Random;

public class ListHeapBenchmark {
    public static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(bound);
        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += 1 + rnd.nextInt(bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int size = 1023;
        int bound = 100_000;
        int tries = 10;
        int accuracy = 10_000;
        int operations = 1000;
        System.out.println("N\t\tPush operation\t\tEnqueue+Dequeue operation\t\tRatio");
        int attempt = 1;
        for (int i = 0; i < tries; i++) {
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;
            for (int j = 0; j < accuracy; j++) {
                min1 = Double.POSITIVE_INFINITY;
                min2 = Double.POSITIVE_INFINITY;
                Random random = new Random();

                ListHeap listHeap1 = new ListHeap();
                for (int t = 0; t < size; t++) {
                    int item = random.nextInt(bound);
                    listHeap1.enqueue(item);
                }

                double start1 = System.nanoTime();
                for (int o = 0; o < operations; o++) {
                    listHeap1.push(random.nextInt(10, 100));
                }
                double time1 = System.nanoTime() - start1;

                if (time1 < min1) {
                    min1 = time1;
                }

                ListHeap listHeap2 = new ListHeap();
                for (int t = 0; t < size; t++) {
                    int item = random.nextInt(100_000);
                    listHeap2.enqueue(item);
                }

                double start2 = System.nanoTime();
                for (int o = 0; o < operations; o++) {
                    int removedItem = listHeap2.dequeue();
                    listHeap2.enqueue(removedItem);
                }
                double time2 = System.nanoTime() - start2;

                if (time2 < min2) {
                    min2 = time2;
                }
            }
            System.out.printf("%d \t\t\t %.2f \t\t\t\t\t %.2f \t\t\t\t\t %.2f\n", attempt, min1 / 1000, min2 / 1000, min1 / min2);
            attempt++;
        }
    }
}

