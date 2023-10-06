import by.example.QuickSortArray;
import by.example.QuickSortList;

import java.util.Random;

public class QuickSortBenchmark {
    public static int[] createArray(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        int nxt = rnd.nextInt(bound);
        for (int i = 0; i < n; i++) {
            array[i] = nxt;
            nxt += 1 + rnd.nextInt(bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800};
        int bound = 100_000;
        int tries = 10_000;
        System.out.println("N\t\tList time\t\tArray time\t\t List function\t\t Array function");
        for (int size : sizes) {
            double min2 = Double.POSITIVE_INFINITY;
            double min1 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                QuickSortArray quickSortArray = new QuickSortArray();
                int[] array = createArray(size, bound);
                int[] clone1 = array.clone();

                double arrayStart = System.nanoTime();
                quickSortArray.sort(clone1);
                double arrayTime = System.nanoTime() - arrayStart;

                if (arrayTime < min2) {
                    min2 = arrayTime;
                }

                QuickSortList quickSortList = new QuickSortList();
                QuickSortList.LinkedList list = new QuickSortList.LinkedList();
                int[] clone2 = array.clone();
                for (int item : clone2) {
                    list.add(item);
                }

                double listStart = System.nanoTime();
                quickSortList.sort(list);
                double listTime = System.nanoTime() - listStart;

                if (listTime < min1) {
                    min1 = listTime;
                }
            }
            double functionList = min1 / size / size;
            double functionArray = min2 / size / size;
            System.out.printf("%d \t\t %.2f \t\t %.2f \t\t\t\t %.2f \t\t\t\t %.2f\n", size, min1 / 1000, min2 / 1000, functionList, functionArray);
        }
    }
}
