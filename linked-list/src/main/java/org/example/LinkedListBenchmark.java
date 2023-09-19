package org.example;

import java.util.Random;

public class LinkedListBenchmark {

    public static int[] createArray(int n) {
        Random rnd = new Random();
        int[] array = new int[n];
        var length = array.length;
        for (int a = 0; a < length; a++) {
            array[a] = rnd.nextInt(n * 10);
        }
        return array;
    }

    public static void appendArrays(int[] arr1, int[] arr2) {
        // Calculate the length of the new array
        int totalLength = arr1.length + arr2.length;

        // Create a new array with the calculated length
        int[] result = new int[totalLength];

        // Copy elements from the first array to the result array
        for (int i = 0; i < arr1.length; i++) {
            result[i] = arr1[i];
        }

        // Copy elements from the second array to the result array
        for (int i = 0; i < arr2.length; i++) {
            result[arr1.length + i] = arr2[i];
        }
    }

    public static void main(String[] arg) {
        int sizes[] = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};
        //The time depends on the size of the array that gets the appendix.
        //If the array is constant, then O(1), if it grows, then O(n), where n is its size
        int sizeB = 100;
        int tries = 1000;

        System.out.printf("Number of elements\t\t Linked list allocation time\t\t Array allocation time\n");
        for (int sizeA : sizes) {

            double minLinked = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                LinkedList a = new LinkedList(sizeA);
                LinkedList b = new LinkedList(sizeB);
                double appendLinkedStart = System.nanoTime();
                a.append(b);
                double executionTime = System.nanoTime() - appendLinkedStart;
                if (executionTime < minLinked) {
                    minLinked = executionTime;
                }
            }

            double minArray = Double.POSITIVE_INFINITY;
            for (int i = 0; i < tries; i++) {
                int[] a = createArray(sizeA);
                int[] b = createArray(sizeB);
                int[] cloneA = a.clone();
                int[] cloneB = b.clone();
                double appendArrayStart = System.nanoTime();
                appendArrays(cloneA,cloneB);
                double executionTime = System.nanoTime() - appendArrayStart;
                if (executionTime < minArray) {
                    minArray = executionTime;
                }
            }
            System.out.printf("%d\t\t %.2f\t\t %.2f\n", sizeA, minLinked, minArray);

        }
//        LinkedList a = new LinkedList(5);
//        a.add(5);
//        a.add(6);
//        a.add(13);
//        a.add(34);
//
//        LinkedList b = new LinkedList(5);
//        b.add(8);
//        b.add(9);
//        b.add(14);
//        b.add(22);
//        a.append(b);
//        System.out.println(a.length());
    }
}
