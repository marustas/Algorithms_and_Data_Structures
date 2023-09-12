package org.example;

import java.util.Random;

public class SortingBenchmark {
    private static int[] createData(int n) {
        Random rnd = new Random();
        int [] array = new int[n];
        for (int a = 0; a < array.length; a++) {
            array[a] = rnd.nextInt(n * 10);
        }
        return array;
    }

    public static void main(String[] arg) {
int[] sizes = {5, 10, 15, 20};
for(int s: sizes){
    int[] array = createData(s);

    System.out.println("BEFORE SORTING:\n");
    for (int j = 0; j < s; j++) {
        System.out.println(array[j]);
    }

    System.out.println("AFTER SORTING:\n");
    InsertionSort.sort(array);
    for (int j = 0; j < s; j++) {
        System.out.println(array[j]);
    }
}
    }
}
