package by.example;

public class Dijkstra {
    private final Path[] done;
    private final Queue queue;

    public Dijkstra(Map map) {
        int n = map.size();
        done = new Path[n];
        queue = new Queue(n);
    }

    public Integer dist(City city) {
        if (city != null && done[city.id] != null)
            return done[city.id].dist;
        return null;
    }

    public void search(City from, City to) {
        Path ex = new Path(from, null, 0);
        queue.add(ex);
        done[from.id] = ex;
        shortest(to);
    }

    private void shortest(City destination) {
        while (!queue.isEmpty()) {
            Path entry = queue.remove();
            City city = entry.city;
            if (city == destination)
                break;
            Integer sofar = entry.dist;
            for (Connection connection : city.neighbours) {
                City to = connection.city;
                if (done[to.id] == null) {
                    Path ex = new Path(to, city, sofar + connection.distance);
                    done[to.id] = ex;
                    queue.add(ex);
                } else {
                    Path ex = done[to.id];
                    if (ex.dist > sofar + connection.distance) {
                        ex.dist = sofar + connection.distance;
                        ex.prev = city;
                        queue.bubble(ex.index);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Map map1 = new Map();
        Dijkstra dijkstra = new Dijkstra(map1);
        String[] trips = {"Malmö,München",
                "Malmö,Stockholm",
                "Malmö,Zürich",
                "Malmö,Milano",
                "Malmö,Oslo",
                "Malmö,Wien",
                "Malmö,Amsterdam",
                "Malmö,Prag",
                "Malmö,Paris",
                "Malmö,Bukarest",
                "Malmö,Barcelona",
                "Malmö,Rom"};
        for (String trip : trips) {
            String[] parts = trip.split(",");
            String cityName1 = parts[0];
            String cityName2 = parts[1];
            City city1 = map1.lookup(cityName1);
            City city2 = map1.lookup(cityName2);
            double start = System.nanoTime();
            dijkstra.search(city1, city2);
            double end = System.nanoTime();
            double time = end - start;
            int n = dijkstra.done.length;
            System.out.printf("Time to travel from %s to %s: %f\t visited cities: %d\n", cityName1, cityName2, time / 1_000_000, n);
        }
    }
}