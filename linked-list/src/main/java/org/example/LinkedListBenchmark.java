package org.example;

public class LinkedListBenchmark {


    public static void main(String[] arg) {
//        int sizes[] = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};
//        //The time depends on the size of the array that gets the appendix.
//        //If the array is constant, then O(1), if it grows, then O(n), where n is its size
//        //int sizeB = 100;
//        int sizeA = 100;
//        int tries = 1000;
//
//        System.out.printf("Number of elements\t\t Time\n");
//        for (int sizeB /*int size A*/ : sizes) {
//
//            double min = Double.POSITIVE_INFINITY;
//            for (int i = 0; i < tries; i++) {
//                LinkedList a = new LinkedList(sizeA);
//                LinkedList b = new LinkedList(sizeB);
//                double appendStart = System.nanoTime();
//                b.append(a);
//                double executionTime = System.nanoTime() - appendStart;
//                if (executionTime < min) {
//                    min = executionTime;
//                }
//            } f j fjk
//            System.out.printf("%d\t\t %.2f\n", sizeA, min);
//
//        }
        LinkedList list1 = new LinkedList(1);
        LinkedList list2 = new LinkedList(1);
        list1.append(list2);
        System.out.println(list1.length());
    }
}
