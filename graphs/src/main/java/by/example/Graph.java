package by.example;

import java.io.BufferedReader;
import java.io.FileReader;
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

    public static class Map {
        String file = "graphs/src/main/java/by/example/trains.csv";
        private City[] cities;
        private final int mod = 541;

        public Map() {
            cities = new City[mod];
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    if (parts.length == 3) {
                        String cityName1 = parts[0];
                        String cityName2 = parts[1];
                        int distance = Integer.parseInt(parts[2]);
                        City city1 = lookup(cityName1);
                        City city2 = lookup(cityName2);
                        city1.connect(city2, distance);
                        city2.connect(city1, distance);
                    }
                }
            } catch (Exception e) {
                System.out.println(" file " + file + " not found or corrupt");
            }
        }


    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}
