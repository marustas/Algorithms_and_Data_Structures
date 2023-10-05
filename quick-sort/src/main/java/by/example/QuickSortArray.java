package by.example;

import java.util.Random;

public class QuickSortArray {
    public void sort(int[] array) {
        int min = 0;
        int max = array.length - 1;
        sortRecursive(array, min, max);
    }

    private static void sortRecursive(int[] array, int min, int max) {
        if (min < max) {
            int pivotIndex = partition(array, min, max);
            sortRecursive(array, min, pivotIndex - 1);
            sortRecursive(array, pivotIndex + 1, max);
        }
    }

    public static int partition(int[] array, int min, int max) {
        int i = min;
        int j = max;
        int pivot = array[min];
        while (true) {
            while (i <= j && array[i] <= pivot) {
                i++;
            }
            while (j >= i && array[j] >= pivot) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            } else {
                int temp = array[min];
                array[min] = array[j];
                array[j] = temp;
                return j;
            }
        }
    }
}