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
        System.out.println("N\t\t FastAdd \t\t FastRemove");
        for (int size : sizes) {
            double min1 = Double.POSITIVE_INFINITY;
            double min2 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                int[] array = createArray(size, bound);

                PriorityQueueFastAdd priorityQueueFastAdd = new PriorityQueueFastAdd();
                int[] clone1 = array.clone();
                for (int item : clone1) {
                    priorityQueueFastAdd.add(item);
                }
                double start1 = System.nanoTime();
                for (int j = 0; j < size; j++) {
                    int item = priorityQueueFastAdd.remove();
                    priorityQueueFastAdd.add(item);
                }
                double time1 = System.nanoTime() - start1;

                if (time1 < min1) {
                    min1 = time1;
                }

                PriorityQueueFastRemove priorityQueueFastRemove = new PriorityQueueFastRemove();
                int[] clone2 = array.clone();
                for (int item : clone2) {
                    priorityQueueFastRemove.add(item);
                }
                double start2 = System.nanoTime();
                for (int l = 0; l < size; l++) {
                    int item = priorityQueueFastRemove.remove();
                    priorityQueueFastRemove.add(item);
                }
                double time2 = System.nanoTime() - start2;

                if (time2 < min2) {
                    min2 = time2;
                }
            }
            System.out.printf("%d\t\t%.2f\t\t%.2f\n", size, min1, min2);
        }
    }
}
