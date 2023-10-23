package by.example;

public class Paths {
    Map.City[] path;
    int sp;

    public Paths() {
        path = new Map.City[54];
        sp = 0;
    }

    public Integer shortest(Map.City from, Map.City to) {
        if (from == to) {
            return 0;
        }
        for (int i = 0; i < sp; i++) {
            if (path[i] == from) {
                return null;
            }
        }
        path[sp++] = from;
        Integer shortest = null;

        for (int i = 0; i < from.neighbours.size(); i++) {
            if (from.neighbours.get(i) != null) {
                Map.Connection conn = from.neighbours.get(i);
                Map.City nextCity = conn.city;
                Integer distance = shortest(nextCity, to);
                if ((distance != null) && ((shortest == null) || (shortest > distance + conn.distance))) {
                    shortest = distance + conn.distance;
                }
            }
        }
        path[sp--] = null;
        return shortest;
    }

    public static void main(String[] args) {
        Map map = new Map("graphs/src/main/java/by/example/trains.csv");
        String[] cityPairs = {
                "Malmö,Göteborg",
                "Göteborg,Stockholm",
                "Malmö,Stockholm,600",
                "Stockholm,Sundsvall",
                "Stockholm,Umeå",
                "Göteborg,Sundsvall",
                "Sundsvall,Umeå",
                "Umeå,Göteborg",
                "Göteborg,Umeå"
        };
        for (String pair : cityPairs) {
            String[] cities = pair.split(",");
            String from = cities[0];
            String to = cities[1];
            Paths paths = new Paths();
            long startTime = System.nanoTime();
            Integer dist = paths.shortest(map.lookup(from), map.lookup(to));
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println(pair + ": shortest path is " + dist + " min (" + duration + " ms)");
        }
    }
}
