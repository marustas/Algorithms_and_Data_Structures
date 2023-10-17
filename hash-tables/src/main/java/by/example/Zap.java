package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Zap {
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

    public String linear(Integer zip) {
        for (Node datum : data) {
            if (datum.code.equals(zip)) {
                return datum.name;
            }
        }
        return null;
    }

    public String binary(Integer zip) {
        int start = 0;
        int end = max;
        while (start <= end) {
            int mid = (start + end) / 2;
            Integer midCode = data[mid].code;
            if (midCode.equals(zip)) {
                return data[mid].name;
            }
            if (midCode > zip) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return null;
    }

    public Zap(String file) {
        data = new Node[100_000];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                String name = row[1];
                Integer pop = Integer.valueOf(row[2]);
                data[i++] = new Node(code, name, pop);
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public static void main(String[] args) {
        String file = "hash-tables/src/main/java/by/example/postnummer.csv";
        Zap zap = new Zap(file);
// converting string to integer takes more time than searching simply for an integer.
        System.out.println(zap.linear(11115));
        System.out.println(zap.binary(11115));
        System.out.println(zap.linear(98499));
        System.out.println(zap.binary(98499));
    }
}
