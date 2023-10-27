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
            System.out.println(sofar);
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
        City city1 = map1.lookup("Umeå");
        City city2 = map1.lookup("Göteborg");
        double start = System.nanoTime();
        dijkstra.search(city1, city2);
        double end = System.nanoTime()-start;
        System.out.println(end/1_000_000);
    }
}