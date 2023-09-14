package org.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class SortingBenchmark {
    @Param({"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000"})
    private int n;
    private int[] DATA_FOR_TESTING;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(SortingBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        DATA_FOR_TESTING = createData(n);
    }

    @Benchmark
    public void mergeSort() {
        MergeSort.sort(DATA_FOR_TESTING);
    }

    @Benchmark
    public void selectionSort() {
        SelectionSort.sort(DATA_FOR_TESTING);
    }

    @Benchmark
    public void insertionSort() {
        InsertionSort.sort(DATA_FOR_TESTING);
    }

    private static int[] createData(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        for (int a = 0; a < array.length; a++) {
            array[a] = rnd.nextInt(n * 10);
        }
        return array;
    }
}

//public class SortingBenchmark {
//    private static int[] createData(int n) {
//        Random rnd = new Random();
//        int[] array = new int[n];
//        for (int a = 0; a < array.length; a++) {
//            array[a] = rnd.nextInt(n * 10);
//        }
//        return array;
//    }
//
//    public static void main(String[] arg) {
//        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000};
//        int loop = 10;
//        int tries = 1000;
//        System.out.printf("Number of elements\t Selection time\t Insertion time\t Merge time\t Ratio \n");
//        for (int s : sizes) {
//            int[] array = createData(s);
//
//            double minSelectTime = Double.POSITIVE_INFINITY;
//            for (int i = 0; i < tries; i++) {
//                double SelectionStart = System.nanoTime();
//                for (int j = 0; j < loop; j++) {
//                    int[] clone = array.clone();
//                    SelectionSort.sort(clone);
//                }
//                double SelectionTime = System.nanoTime() - SelectionStart;
//                if (SelectionTime < minSelectTime) {
//                    minSelectTime = SelectionTime;
//                }
//            }
//
//            double minInsertTime = Double.POSITIVE_INFINITY;
//            for (int i = 0; i < tries; i++) {
//                double InsertionStart = System.nanoTime();
//                for (int j = 0; j < loop; j++) {
//                    int[] clone = array.clone();
//                    InsertionSort.sort(clone);
//                }
//                double InsertionTime = System.nanoTime() - InsertionStart;
//                if (InsertionTime < minInsertTime) {
//                    minInsertTime = InsertionTime;
//                }
//            }
//
//            double minMergeTime = Double.POSITIVE_INFINITY;
//            for (int i = 0; i < tries; i++) {
//                double MergeStart = System.nanoTime();
//                for (int j = 0; j < loop; j++) {
//                    int[] clone = array.clone();
//                    MergeSort.sort(clone);
//                }
//                double MergeTime = System.nanoTime() - MergeStart;
//                if (MergeTime < minMergeTime) {
//                    minMergeTime = MergeTime;
//                }
//            }
//
//            double ratio = (minInsertTime / (loop * tries)) / (minSelectTime / (loop * tries));
//            System.out.printf("%d\t\t\t\t%8.0f\t\t\t%8.0f\t\t%8.0f\t%.2f\n", s, minSelectTime / (loop * tries), minInsertTime / (loop * tries), minMergeTime / (loop * tries), ratio);
//
//        }
//    }
//}
