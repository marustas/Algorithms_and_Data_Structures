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
        else
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
        Map map = new Map();
        City test = map.lookup("Malmö");
        Dijkstra dijkstra = new Dijkstra(map);
        dijkstra.search(test, null);
        String[] trips = {
                "Malmö,Kiruna",
                "Malmö,München",
                "Malmö,Stockholm",
                "Malmö,Zürich",
                "Malmö,Milano",
                "Malmö,Paris",
                "Malmö,Bukarest",
                "Malmö,Barcelona",
                "Malmö,Rom"
        };
        for (String trip : trips) {
            String[] parts = trip.split(",");
            City from = map.lookup(parts[0]);
            City to = map.lookup(parts[1]);
            double start = System.nanoTime();
            dijkstra.search(from, to);
            double end = System.nanoTime();
            double time = (end - start) / 1000;
            Integer dist = dijkstra.dist(to);
            Integer stops = 0;
            for (City prv = to; prv != null; prv = dijkstra.done[prv.id].prev) {
                stops++;
            }
            if (to != null) {
                System.out.println("Trip from " + from.name + " to " + to.name + " takes " + dist + " minutes");
                System.out.printf("Search time: %.2f ms\t Number of stops: %d\n", time, stops);
            }
        }
    }
}