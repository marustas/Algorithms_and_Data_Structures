package by.example;

import by.example.LinkedList.Cell;

import java.util.Random;

public class LinkedListBenchmark {

    public static void main(String[] arg) {
        var random = new Random();
        int k = 1_000;
        int tries = 10_000;
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        System.out.print("Number of elements\t\t Time\n");
        for (int size : sizes) {
            double minA = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                LinkedList list = new LinkedList(size);
                // fill the list with sequential numbers
                for (int i = 0; i < size; i++) {
                    list.add(i);
                }
                Cell[] references = list.getReferences();
                int[] randomIndexes = random.ints(k, 0, size - 1).toArray();
                double start = System.nanoTime();
                for (int randomIndex : randomIndexes) {
                    // remove and insert the random element k times
                    // (k is the number of operations performed in one iteration)
                    var randomReference = references[randomIndex];
                    boolean result = list.findCell(randomReference);
                }
                double executionTime = System.nanoTime() - start;

                if (executionTime < minA) {
                    minA = executionTime;
                }
            }
            System.out.printf("%d\t\t %.2f\n", size, minA);
        }
    }

}
