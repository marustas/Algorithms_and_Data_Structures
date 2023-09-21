package org.example;

import java.util.Random;

public class LinkedListBenchmark {

    public static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        var length = array.length;
        for (int a = 0; a < length; a++) {
            array[a] = rnd.nextInt(bound - 1);
        }
        return array;
    }

//    public static void appendArrays(int[] arr1, int[] arr2) {
//        // Calculate the length of the new array
//        int totalLength = arr1.length + arr2.length;
//
//        // Create a new array with the calculated length
//        int[] result = new int[totalLength];
//
//        // Copy elements from the first array to the result array
//        for (int i = 0; i < arr1.length; i++) {
//            result[i] = arr1[i];
//        }
//
//        // Copy elements from the second array to the result array
//        for (int i = 0; i < arr2.length; i++) {
//            result[arr1.length + i] = arr2[i];
//        }
//    }

    public static void main(String[] arg) {
//        int sizes[] = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};
//        //The time depends on the size of the array that gets the appendix.
//        //If the array is constant, then O(1), if it grows, then O(n), where n is its size§
//        int tries = 1000;
//        System.out.printf("Number of elements\t\t Ratio\n");
//        for (int size : sizes) {
//
//            double minList = Double.POSITIVE_INFINITY;
//            for (int i = 0; i < tries; i++) {
//                double appendStart = System.nanoTime();
//                LinkedList list = new LinkedList(size);
//                double listExecutionTime = System.nanoTime() - appendStart;
//                if (listExecutionTime < minList) {
//                    minList = listExecutionTime;
//                }
//            }
//
//            double minArray = Double.POSITIVE_INFINITY;
//            for (int i = 0; i < tries; i++) {
//                double appendArrayStart = System.nanoTime();
//                int[] array = createArray(size);
//                double executionTime = System.nanoTime() - appendArrayStart;
//                if (executionTime < minArray) {
//                    minArray = executionTime;
//                }
//            }
//
//            double ratio = minList/minArray;
//            System.out.printf("%d\t\t %.2f\n", size, ratio);
//        }
        int k = 1000;
        int tries = 100;
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        System.out.print("Number of elements\t\t Time\n");
        for (int s : sizes) {
            double minA = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                LinkedList list = new LinkedList(s);
                LinkedList.Cell[] references = list.createCellReferences();
                int[] indices = createArray(k, s);

                double start = System.nanoTime();
                for (int index : indices) {
                    list.unlink(references[index]);
                    list.insert(references[index]);
                }
                double executionTime = System.nanoTime() - start;

                if (executionTime < minA) {
                    minA = executionTime;
                }

            }
            System.out.printf("%d\t\t %.2f\n", s, minA/1000);
        }
    }
}
