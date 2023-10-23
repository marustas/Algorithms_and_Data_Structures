package by.example;

public class Better {
    Map.City[] path;
    int sp;

    public Better() {
        path = new Map.City[1024];
        sp = 0;
    }

    public Integer shortest(Map.City from, Map.City to, Integer max) {
        if ((max != null) && (max < 0)) {
            return null;
        }
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
                Integer distance = shortest(nextCity, to, (max != null) ? max - conn.distance : null);
                if ((distance != null) && ((shortest == null) || (shortest > distance + conn.distance))) {
                    shortest = distance + conn.distance;
                }
                if ((shortest != null) && ((max == null) || (max > shortest))) {
                    max = shortest;
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
                "Malmö,Stockholm",
                "Stockholm,Sundsvall",
                "Stockholm,Umeå",
                "Göteborg,Sundsvall",
                "Sundsvall,Umeå",
                "Umeå,Göteborg",
                "Göteborg,Umeå",
                "Malmö,Kiruna"
        };
        for (String pair : cityPairs) {
            String[] cities = pair.split(",");
            String from = cities[0];
            String to = cities[1];
            Better better = new Better();
            long startTime = System.nanoTime();
            Integer dist = better.shortest(map.lookup(from), map.lookup(to), null);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            System.out.println(pair + ": shortest path is " + dist + " min (" + duration + " ms)");
        }
    }
}
