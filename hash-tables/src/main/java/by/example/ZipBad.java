package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class ZipBad {
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

    public ZipBad(String file) {
        data = new Node[10_000];
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
        ZipBad zipBad = new ZipBad(file);

        double min1 = Double.POSITIVE_INFINITY;
        double min2 = Double.POSITIVE_INFINITY;
        double min3 = Double.POSITIVE_INFINITY;
        double min4 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < 10_000; i++) {
            double start1 = System.nanoTime();
            zipBad.linear(11115);
            double time1 = System.nanoTime() - start1;
            if (min1 > time1)
                min1 = time1;

            double start2 = System.nanoTime();
            zipBad.binary(11115);
            double time2 = System.nanoTime() - start2;
            if (min2 > time2)
                min2 = time2;

            double start3 = System.nanoTime();
            zipBad.linear(98499);
            double time3 = System.nanoTime() - start3;
            if (min3 > time3)
                min3 = time3;

            double start4 = System.nanoTime();
            zipBad.binary(98499);
            double time4 = System.nanoTime() - start4;
            if (min4 > time4)
                min4 = time4;
        }
        System.out.printf("Searching for first: linear(%.2f)\t\tbinary(%.2f)\nSearching for last: linear(%.2f)\t\tbinary(%.2f)\n", min1, min2, min3, min4);

        System.out.println(zipBad.linear(11115));
        System.out.println(zipBad.binary(11115));
        System.out.println(zipBad.linear(98499));
        System.out.println(zipBad.binary(98499));
    }
}
