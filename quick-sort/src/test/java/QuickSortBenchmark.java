import by.example.QuickSortArray;
import by.example.QuickSortList;

import java.util.Random;

public class QuickSortBenchmark {
    public static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        var length = array.length;
        for (int a = 0; a < length; a++) {
            array[a] = rnd.nextInt(bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        int bound = 100_000;
        int tries = 100_000;
        System.out.println("Number of elements\t\t List time \t\t Array time");
        for (int size : sizes) {
            double min1 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                QuickSortList quickSortList = new QuickSortList();
                Random random = new Random();
                QuickSortList.LinkedList list = new QuickSortList.LinkedList();
                for (int i = 0; i < size; i++) {
                    list.add(random.nextInt(bound));
                }

                double listStart = System.nanoTime();
                quickSortList.sort(list);
                double listTime = System.nanoTime() - listStart;

                if (listTime < min1) {
                    min1 = listTime;
                }
            }

            double min2 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                QuickSortArray quickSortArray = new QuickSortArray();
                int[] array = createArray(size, bound);

                double arrayStart = System.nanoTime();
                int[] clone = array.clone();
                quickSortArray.sort(clone);
                double arrayTime = System.nanoTime() - arrayStart;

                if (arrayTime < min2) {
                    min2 = arrayTime;
                }
            }

            System.out.printf("%d \t\t %.2f \t\t %.2f\n", size, min1 / 1000, min2 / 1000);
        }

        System.out.println();
    }
}
