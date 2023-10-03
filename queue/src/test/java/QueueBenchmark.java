import by.example.QueueArray;
import by.example.QueueList;

import java.util.Random;

public class QueueBenchmark {
    public static void main(String[] args) {
        var random = new Random();
        int k = 1_000;
        int tries = 100_000;
        int bound = 100_000;
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        System.out.print("Number of nodes \t Array queue \t List queue\n");
        for (int size : sizes) {

            double min1 = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                QueueList<Integer> queueList = new QueueList<Integer>();
                for (int j = 0; j < size; j++) {
                    queueList.add(random.nextInt(bound));
                }

                double listStart = System.nanoTime();
                for (int j = 0; j < size; j++) {
                    queueList.remove();
                }
                double queueListTime = System.nanoTime() - listStart;

                if (queueListTime < min1) {
                    min1 = queueListTime;
                }
            }

            double min2 = Double.POSITIVE_INFINITY;
            for (int l = 0; l < tries; l++) {
                QueueArray queueArray = new QueueArray();
                for (int j = 0; j < size; j++) {
                    queueArray.add(random.nextInt(bound));
                }

                double arrayStart = System.nanoTime();
                for (int j = 0; j < size; j++) {
                    queueArray.remove();
                }
                double queueArrayTime = System.nanoTime() - arrayStart;

                if (queueArrayTime < min2) {
                    min2 = queueArrayTime;
                }
            }
            System.out.printf("%d\t\t\t\t%.2f\t\t %.2f\n", size, min2 / 1000, min1 / 1000);
        }
    }
}
