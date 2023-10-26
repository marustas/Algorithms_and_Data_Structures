package by.example;

import java.util.ArrayList;
import java.util.List;

public class City {
    String name;
    public List<Connection> neighbours;

    public City(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

    public void connect(City next, int distance) {
        Connection connection = new Connection(next, distance);
        neighbours.add(connection);
    }
}
