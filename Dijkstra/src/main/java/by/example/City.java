package by.example;

import java.util.ArrayList;
import java.util.List;

public class City {
    String name;
    public List<Connection> neighbours;
    public Integer id;

    public City(String name, Integer i) {
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.id = i;
    }

    public void connect(City next, int distance) {
        Connection connection = new Connection(next, distance);
        neighbours.add(connection);
    }
}
