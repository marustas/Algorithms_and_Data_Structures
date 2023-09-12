package org.example;

public class InsertionSort {
    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0 && (array[j] < array[j - 1]); j--) {
                swap(array, j, j - 1);
            }
        }
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}