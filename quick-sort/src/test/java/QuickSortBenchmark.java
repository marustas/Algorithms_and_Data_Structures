import by.example.QuickSortList;

import java.util.Random;

public class QuickSortBenchmark {
    public static void main(String[] args) {
        QuickSortList quickSortList = new QuickSortList();
        Random random = new Random();
        QuickSortList.LinkedList list = new QuickSortList.LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        quickSortList.sort(list);
        System.out.println();
    }
}
