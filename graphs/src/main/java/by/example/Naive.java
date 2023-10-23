package by.example;

public class Naive {
    public static void main(String[] args) {
        Map map = new Map("graphs/src/main/java/by/example/trains.csv");
        String[] cityPairs = {
                "Malmö,Göteborg,600",
                "Göteborg,Stockholm,600",
                "Malmö,Stockholm,600",
                "Stockholm,Sundsvall,600",
                "Stockholm,Umeå,600",
                "Göteborg,Sundsvall,600",
                "Sundsvall,Umeå,600",
                "Umeå,Göteborg,600",
                "Göteborg,Umeå,600"
        };
        for (String pair : cityPairs) {
            String[] cities = pair.split(",");
            String from = cities[0];
            String to = cities[1];
            Integer max = Integer.valueOf(cities[2]);
            long startTime = System.nanoTime();
            Integer dist = shortest(map.lookup(from), map.lookup(to), max);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println(pair + ": shortest path is " + dist + " min (" + duration + " ms)");
        }
    }

    private static Integer shortest(Map.City from, Map.City to, Integer max) {
        if (max < 0)
            return null;
        if (from == to)
            return 0;

        Integer shortest = null;

        for (int i = 0; i < from.neighbours.size(); i++) {
            if (from.neighbours.get(i) != null) {
                Map.Connection conn = from.neighbours.get(i);
                Map.City nextCity = conn.city;
                Integer distance = shortest(nextCity, to, max - conn.distance);
                if ((distance != null) && ((shortest == null) || (shortest > distance + conn.distance))) {
                    shortest = distance + conn.distance;
                }
                if ((shortest != null) && (max > shortest)) {
                    max = shortest;
                }
            }
        }
        return shortest;
    }
}
