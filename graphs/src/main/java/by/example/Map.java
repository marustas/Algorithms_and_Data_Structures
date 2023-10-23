package by.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    public static class City {
        String name;
        private List<Connection> neighbours;

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

    private final City[] cities;

    public Map(String file) {
        cities = new City[900];
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
                    city2.connect(city1, distance);
                }
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found or corrupt");
        }
    }

    public City lookup(String cityName) {
        int index = hash(cityName);
        while (cities[index] != null) {
            if (cities[index].name.equals(cityName)) {
                return cities[index];
            }
            index++;
        }
        City newCity = new City(cityName);
        cities[index] = newCity;
        return newCity;
    }

    private Integer hash(String name) {
        int hash = 0;
        int mod = 541;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public static void main(String[] args) {
        Map map = new Map("graphs/src/main/java/by/example/trains.csv");
        System.out.println(Arrays.toString(map.cities));
    }
}