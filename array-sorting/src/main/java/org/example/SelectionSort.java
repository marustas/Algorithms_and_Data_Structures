package org.example;

public class SelectionSort {
    private static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int candidate = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[candidate]) {
                    candidate = j;
                }
            }
            swap(array, i, candidate);
        }
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}