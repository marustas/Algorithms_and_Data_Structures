import by.example.Queue;

public class Benchmark {
    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.add(10);
        queue.add(15);
        queue.add(7);
        queue.add(23);
        queue.add(18);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
