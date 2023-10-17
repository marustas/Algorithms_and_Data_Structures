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
            max = data.length - 1;
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
// converting string to integer takes more time than searching simply for an integer.
        double min1 = Double.POSITIVE_INFINITY;
        double min2 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < 10_000; i++) {
            double start1 = System.nanoTime();
            zip.lookup(11115);
            double time1 = System.nanoTime() - start1;
            if (min1 > time1)
                min1 = time1;
            double start2 = System.nanoTime();
            zip.lookup(98499);
            double time2 = System.nanoTime() - start2;
            if (min2 > time2)
                min2 = time2;
        }
        System.out.printf("Searching for first: %.2f, Searching for last: %.2f\n", min1, min2);
        System.out.println(zip.lookup(11115));
        System.out.println(zip.lookup(98499));
    }
}