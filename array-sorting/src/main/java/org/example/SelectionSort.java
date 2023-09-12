package org.example;

public class SelectionSort {
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int candidate = array[i];
            for (int j = i; j < array.length; j++) {
                if (candidate > array[j]) {
                    candidate = array[j];
                }
            }
            array[i] = candidate;
        }
    }
}