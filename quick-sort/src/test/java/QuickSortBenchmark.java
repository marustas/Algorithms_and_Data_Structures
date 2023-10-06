import by.example.QuickSortArray;
import by.example.QuickSortList;

import java.util.Random;

public class QuickSortBenchmark {
    public static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        var length = array.length;
        array[0] = 100001;
        for (int a = 1; a < length; a++) {
            array[a] = rnd.nextInt(bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800};
        int bound = 100_000;
        int tries = 100_000;
        int warmup = 10_000;
        System.out.println("N\t\tList time\t\tArray time\t\t List function\t\t Array function");
        for (int size : sizes) {

            for (int t = 0; t < warmup; t++) {
                QuickSortArray quickSortArray = new QuickSortArray();
                int[] array = createArray(size, bound);
                int[] clone = array.clone();
                quickSortArray.sort(clone);

                QuickSortList quickSortList = new QuickSortList();
                QuickSortList.LinkedList list = new QuickSortList.LinkedList();
                for (int i = 0; i < size; i++) {
                    list.add(array[i]);
                }
                quickSortList.sort(list);
            }

            double min2 = Double.POSITIVE_INFINITY;
            double min1 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                QuickSortArray quickSortArray = new QuickSortArray();
                int[] array = createArray(size, bound);
                int[] clone = array.clone();

                double arrayStart = System.nanoTime();
                quickSortArray.sort(clone);
                double arrayTime = System.nanoTime() - arrayStart;

                if (arrayTime < min2) {
                    min2 = arrayTime;
                }

                QuickSortList quickSortList = new QuickSortList();
                QuickSortList.LinkedList list = new QuickSortList.LinkedList();
                for (int item : array) {
                    list.add(item);
                }

                double listStart = System.nanoTime();
                quickSortList.sort(list);
                double listTime = System.nanoTime() - listStart;

                if (listTime < min1) {
                    min1 = listTime;
                }
            }
            double functionList = min1 / size / Math.log(size);
            double functionArray = min2 / size / Math.log(size);
            System.out.printf("%d \t\t %.2f \t\t %.2f \t\t\t\t %.2f \t\t\t\t %.2f\n", size, min1 / 1000, min2 / 1000, functionList, functionArray);
        }

        System.out.println();
    }
}
