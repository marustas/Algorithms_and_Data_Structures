package org.example;

import java.util.Arrays;
import java.util.Random;

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
//        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000, 13000, 14000, 15000};
        int[] array = createData(10);
        System.out.println(Arrays.toString(array));

        MergeSort.sort(array);
        System.out.println(Arrays.toString(array));

//        int loop = 10;
//        int tries = 1000;
//        System.out.printf("Number of elements\t Selection time\t Insertion time\t Ratio\n");
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
//            double ratio = (minInsertTime / (loop * tries)) / (minSelectTime / (loop * tries));
//            System.out.printf("%d\t\t\t\t%8.0f\t\t\t%8.0f\t\t%.2f\n", s, minSelectTime / (loop * tries), minInsertTime / (loop * tries), ratio);
//
//        }
    }
}
