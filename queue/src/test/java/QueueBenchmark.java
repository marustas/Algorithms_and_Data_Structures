import by.example.QueueArray;

public class QueueBenchmark {
    public static void main(String[] args) {
        QueueArray queueArray = new QueueArray();
        queueArray.add(5);
        queueArray.add(8);
        queueArray.add(17);
        queueArray.add(13);
        queueArray.add(22);
        queueArray.add(59);
        queueArray.add(78);
        System.out.println(queueArray.remove());
        System.out.println(queueArray.remove());
        System.out.println(queueArray.remove());
        System.out.println(queueArray.remove());
    }
}
