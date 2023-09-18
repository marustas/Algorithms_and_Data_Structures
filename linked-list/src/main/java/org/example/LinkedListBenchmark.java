package org.example;

public class LinkedListBenchmark {


    public static void main(String[] arg) {
        int sizes[] = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};
        int sizeB = 100;
        int tries = 1000;

        System.out.printf("Number of elements\t\t Time\n");
        for (int sizeA : sizes) {

            double min = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                LinkedList a = new LinkedList(sizeA);
                LinkedList b = new LinkedList(sizeB);
                double appendStart = System.nanoTime();
                    b.append(a);
                double executionTime = System.nanoTime() - appendStart;
                if (executionTime < min) {
                    min = executionTime;
                }
            }
            System.out.printf("%d\t\t %.2f\n", sizeA, min);

        }
    }
}
