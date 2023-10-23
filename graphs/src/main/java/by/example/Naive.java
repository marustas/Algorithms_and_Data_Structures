package by.example;

public class Naive {
    public static void main(String[] args) {
        Map map = new Map("graphs/src/main/java/by/example/trains.csv");
        String from = args[0];
        String to = args[1];
        Integer max = Integer.valueOf(args[2]);
        long t0 = System.nanoTime();
        Integer dist = shortest(map.lookup(from), map.lookup(to), max);
        long time = (System.nanoTime() - t0) / 1_000_000;
        System.out.println("shortest: " + dist + " min (" + time + " ms)");
    }

    private static Integer shortest(Map.City from, Map.City to, Integer max) {
        if (max < 0)
            return null;
        if (from == to)
            return 0;

        Integer shrt = null;
        int connections = from.neighbours.size();
        for (int i = 0; i < connections; i++) {
            Map.Connection connection = from.neighbours.get(i);
            if (connection != null) {
                int distance = connection.distance;
                Map.City nextCity = connection.city;
                if (max - distance >= 0) {
                    Integer subShortest = shortest(nextCity, to, max - distance);
                    if (subShortest != null) {
                        int totalDistance = distance + subShortest;
                        if (shrt == null || totalDistance < shrt) {
                            shrt = totalDistance;
                        }
                    }
                }
            }
        }
        return shrt;
    }
}
