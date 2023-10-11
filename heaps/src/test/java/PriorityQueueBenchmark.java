import by.example.PriorityQueueFastAdd;
import by.example.PriorityQueueFastRemove;

import java.util.Random;

public class PriorityQueueBenchmark {
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
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800};
        int bound = 100_000;
        int tries = 10_000;
        System.out.println("N\t\tFastAdd Remove time\t\tFastRemove Remove time\t\tFastAdd Add time\t\tFastRemove Add time ");
        for (int size : sizes) {
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;
            double min3 = Double.POSITIVE_INFINITY;
            double min4 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                int[] array = createArray(size, bound);

                PriorityQueueFastAdd priorityQueueFastAdd = new PriorityQueueFastAdd();
                int[] clone1 = array.clone();

                double start3 = System.nanoTime();
                for (int item : clone1) {
                    priorityQueueFastAdd.add(item);
                }
                double time3 = System.nanoTime() - start3;
                if (time3 < min3) {
                    min3 = time3;
                }

                double start1 = System.nanoTime();
                for (int j = 0; j < size; j++) {
                    priorityQueueFastAdd.remove();
                }
                double time1 = System.nanoTime() - start1;
                if (time1 < min1) {
                    min1 = time1;
                }

                PriorityQueueFastRemove priorityQueueFastRemove = new PriorityQueueFastRemove();
                int[] clone2 = array.clone();

                double start4 = System.nanoTime();
                for (int item : clone2) {
                    priorityQueueFastRemove.add(item);
                }
                double time4 = System.nanoTime() - start4;
                if (time4 < min4) {
                    min4 = time4;
                }

                double start2 = System.nanoTime();
                for (int l = 0; l < size; l++) {
                    priorityQueueFastRemove.remove();
                }
                double time2 = System.nanoTime() - start2;

                if (time2 < min2) {
                    min2 = time2;
                }
            }
            System.out.printf("%d\t\t\t%.2f\t\t\t\t\t%.2f\t\t\t\t\t\t%.2f\t\t\t\t%.2f\n", size, min1/size, min2/size, min3/size, min4/size);
        }
    }
}
