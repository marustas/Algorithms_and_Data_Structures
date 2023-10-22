package by.example;

public class Naive {
    public static void main(String[] args) {
        Map map = new Map("graphs/src/main/java/by/example/trains.csv")
        String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);
        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0) / 1_000_000;
        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }
}
