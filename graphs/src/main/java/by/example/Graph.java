package by.example;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public static class City {
        String name;
        private List<Connection> neighbours;

        public City(String name) {
            this.name = name;
            this.neighbours = new ArrayList<>();
            ;
        }

        public void connect(City next, int distance) {
            Connection connection = new Connection(next, distance);
            neighbours.add(connection);
        }
    }

    public static class Connection {
        City city;
        int distance;

        public Connection(City city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    private Integer hash(String name) {
        int hash = 0;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % 541) + name.charAt(i);
        }
        return hash % 541;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
