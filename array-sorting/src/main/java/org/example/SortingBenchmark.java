package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

//@State(Scope.Benchmark)
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
//public class SortingBenchmark {
//    @Param({"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"})
//    private int n;
//    private int[] DATA_FOR_TESTING;
//
//    public static void main(String[] args) throws RunnerException {
//
//        Options opt = new OptionsBuilder()
//                .include(SortingBenchmark.class.getSimpleName())
//                .forks(1)
//                .build();
//        new Runner(opt).run();
//    }
//
//    @Setup
//    public void setup() {
//        DATA_FOR_TESTING = createData(n);
//    }
//
//    @Benchmark
//    public void mergeSort() {
//        MergeSort.sort(DATA_FOR_TESTING);
//    }
//
//    @Benchmark
//    public void selectionSort() {
//        SelectionSort.sort(DATA_FOR_TESTING);
//    }
//
//    @Benchmark
//    public void insertionSort() {
//        InsertionSort.sort(DATA_FOR_TESTING);
//    }
//
//    private static int[] createData(int n) {
//        Random rnd = new Random();
//        int[] array = new int[n];
//        for (int a = 0; a < array.length; a++) {
//            array[a] = rnd.nextInt(n * 10);
//        }
//        return array;
//    }
//}

public class SortingBenchmark {
    private static int[] createData(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int a = 0; a < array.length; a++) {
            array[a] = rnd.nextInt(n * 10);
        }
        return array;
    }

    public static void main(String[] arg) {
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000};
        int loop = 10;
        int tries = 100;
        System.out.printf("Number of elements\t Selection time\t Insertion time\t Merge time \t Improved Merge time\n");
        for (int s : sizes) {
            int[] array = createData(s);

            double minSelectTime = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                double SelectionStart = System.nanoTime();
                for (int j = 0; j < loop; j++) {
                    int[] clone = array.clone();
                    SelectionSort.sort(clone);
                }
                double SelectionTime = System.nanoTime() - SelectionStart;
                if (SelectionTime < minSelectTime) {
                    minSelectTime = SelectionTime;
                }
            }

            double minInsertTime = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                double InsertionStart = System.nanoTime();
                for (int j = 0; j < loop; j++) {
                    int[] clone = array.clone();
                    InsertionSort.sort(clone);
                }
                double InsertionTime = System.nanoTime() - InsertionStart;
                if (InsertionTime < minInsertTime) {
                    minInsertTime = InsertionTime;
                }
            }

            double minMergeTime = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                double MergeStart = System.nanoTime();
                for (int j = 0; j < loop; j++) {
                    int[] clone = array.clone();
                    MergeSort.sort(clone);
                }
                double MergeTime = System.nanoTime() - MergeStart;
                if (MergeTime < minMergeTime) {
                    minMergeTime = MergeTime;
                }
            }

            double minImprovedMergeTime = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                double ImprovedMergeStart = System.nanoTime();
                for (int j = 0; j < loop; j++) {
                    int[] clone = array.clone();
                    ImprovedMergeSort.sort(clone);
                }
                double ImprovedMergeTime = System.nanoTime() - ImprovedMergeStart;
                if (ImprovedMergeTime < minImprovedMergeTime) {
                    minImprovedMergeTime = ImprovedMergeTime;
                }
            }

            double insertionFunction = minInsertTime / (loop * s * s ) * 10;
            double selectionFunction = minSelectTime / (loop * s * s) * 10;
            double mergeFunction = minMergeTime / (loop * s * Math.log(s)) * 10;
            System.out.printf("%d\t\t\t\t%.2f\t\t\t%.2f\t\t%.2f\t\t%.2f\n", s, minSelectTime/(loop*1000), minInsertTime/(loop*1000),minMergeTime/(loop*1000), minImprovedMergeTime/(loop*1000));

        }
    }
}
