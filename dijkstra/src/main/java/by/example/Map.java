package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Map {
    String file = "dijkstra/src/main/java/by/example/europe.csv";
    private final City[] cities;
    private final int mod = 541;
    private int size = 0;

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
                    city2.connect(city1, distance);
                }
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private Integer hash(String name) {
        int hash = 3;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash * 31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }

    public Integer size() {
        return size;
    }

    public City lookup(String name) {
        Integer index = hash(name);
        while (true) {
            if (cities[index] == null) {
                City city = new City(name, size());
                size++;
                cities[index] = city;
                return city;
            }
            if (cities[index].name.equals(name))
                return cities[index];
            index++;
        }
    }
}
