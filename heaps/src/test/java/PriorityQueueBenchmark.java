import by.example.PriorityQueueFastAdd;
import by.example.PriorityQueueFastRemove;

import java.util.Random;

public class PriorityQueueBenchmark {
    public static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rnd.nextInt(bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int increment;
        int[] sizes = {100, 200, 400, 800, 1600};
        int bound = 100_000;
        int tries = 10_000;
        System.out.println("N\t\tFastAdd time\t\tFastRemove time");
        for (int size : sizes) {
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                int[] array = createArray(size, bound);
                int[] increments = createArray(1000, size);

                PriorityQueueFastAdd priorityQueueFastAdd = new PriorityQueueFastAdd();
                int[] clone = array.clone();
                for (int item : clone) {
                    priorityQueueFastAdd.add(item);
                }

                double start1 = System.nanoTime();
                for (int j = 0; j < 1000; j++) {
                    int item = priorityQueueFastAdd.remove();
                    increment = increments[j];
                    priorityQueueFastAdd.add(item + increment);
                }
                double time1 = System.nanoTime() - start1;
                if (time1 < min1) {
                    min1 = time1;
                }

                PriorityQueueFastRemove priorityQueueFastRemove = new PriorityQueueFastRemove();
                for (int item : clone) {
                    priorityQueueFastRemove.add(item);
                }

                double start2 = System.nanoTime();
                for (int l = 0; l < 1000; l++) {
                    int item = priorityQueueFastRemove.remove();
                    increment = increments[l];
                    priorityQueueFastRemove.add(item + increment);
                }
                double time2 = System.nanoTime() - start2;

                if (time2 < min2) {
                    min2 = time2;
                }
            }
            System.out.printf("%d\t\t\t%.2f\t\t\t\t\t%.2f\n", size, min1/1000, min2/1000);
        }
    }
}
