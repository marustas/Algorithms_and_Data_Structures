package by.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    public static class City {
        String name;
        private final List<Connection> neighbours;

        public City(String name) {
            this.name = name;
            this.neighbours = new ArrayList<>();
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
        private final City[] cities;
        private final int mod = 541;

        public Map() {
            cities = new City[mod];
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String cityName1 = parts[0];
                        String cityName2 = parts[1];
                        int distance = Integer.parseInt(parts[2]);
                        City city1 = lookup(cityName1);
                        City city2 = lookup(cityName2);
                        city1.connect(city2, distance);
                    }
                }
            } catch (Exception e) {
                System.out.println(" file " + file + " not found or corrupt");
            }
        }

        private City lookup(String cityName) {
            int index = hash(cityName);
            if (cities[index] == null) {
                cities[index] = new City(cityName);
            }
            return cities[index];
        }

        private Integer hash(String name) {
            int hash = 0;
            for (int i = 0; i < name.length(); i++) {
                hash = (hash * 31 % mod) + name.charAt(i);
            }
            return hash % mod;
        }

    }

    public static void main(String[] args) {
        Map map = new Map();
        System.out.println(Arrays.toString(map.cities));
    }
}
