package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    Node[] data;
    int max;

    public static class Node {
        Integer code;
        String name;
        Integer pop;

        private Node(Integer code, String name, Integer pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    public String lookup(Integer zip) {
        if (data[zip] != null) {
            return data[zip].name;
        }
        return null;
    }

    public Zip(String file) {
        data = new Node[100_000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                String name = row[1];
                Integer pop = Integer.valueOf(row[2]);
                data[code] = new Node(code, name, pop);
            }
            max = data.length-1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private Integer hash(Integer code) {
        return code % 13513;
    }

    public static void main(String[] args) {
        String file = "hash-tables/src/main/java/by/example/postnummer.csv";
        Zip zip = new Zip(file);
// converting string to integer takes more time than searchin simply for an integer.
        System.out.println(zip.lookup(11115));
        System.out.println(zip.lookup(98499));
    }
}