package by.example.benchmark;

import java.util.Random;

import by.example.impl.Binary;

class ConsoleBenchmark {

    public static void main(String[] arg) {

        Binary binary = new Binary();
        int[] sizes = { 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000 };
        var random = new Random();
        System.out.printf("| %-7s | %n", "n", "time");
        for (int size : sizes) {
            int bound = 100_000;
            int k = 1000;
            int[] array = createData(size, bound);
            int tries = 1000;
            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < tries; i++) {
                int[] randomKeys = new int[k];
                for (int j = 0; j < k; j++) {
                    randomKeys[i] = random.nextInt(bound);
                }
                double t0 = System.nanoTime();
                for (int j = 0; j < randomKeys.length; j++) {
                    binary.search(array, randomKeys[j]);
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min) {
                    min = t;
                }
            }

            System.out.printf("%8d | %.2f\n", size, min / 1000);

        }
    }

    private static int[] createData(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(bound);

        for (int i = 0; i < n; i++) {
            array[i] = nxt;
        }
        return array;
    }

    private static int[] keys(int loop, int n) {
        Random rnd = new Random();
        int[] indx = new int[loop];
        for (int i = 0; i < loop; i++) {
            indx[i] = rnd.nextInt(n);
        }
        return indx;
    }
}
