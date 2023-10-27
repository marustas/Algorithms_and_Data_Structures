package by.example;

public class Dijkstra {
    private Path[] done;
    private Queue queue;
    private Map map;

public Dijkstra(Map map){
    int n = map.size;
    done = new Path[n];
    queue = new Queue(n);
}

public Integer dist(City city){
    if(city != null && done[city.id] != null)
        return done[city.id].dist;
    return null;
}

    public void search(City from, City to){
Path ex = new Path(from);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}