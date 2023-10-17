package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {
    int[] keys;
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
            if (datum != null && datum.code.equals(zip)) {
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
            if (data[mid] != null) {
                if (data[mid].code.equals(zip)) {
                    return data[mid].name;
                }
                if (data[mid].code > zip) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (mid > zip) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return null;
    }

    public Zip(String file) {
        data = new Node[100_000];
        keys = new int[100_000];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                keys[i] = code;
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

    public void collisions(int mod) {
        int[] data = new int[mod];
        int[] cols = new int[10];
        for (int i = 0; i < max; i++) {
            Integer index = keys[i] % mod;
            cols[data[index]]++;
            data[index]++;
        }
        System.out.print(mod);
        for (int i = 0; i < 10; i++) {
            System.out.print("\t" + cols[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String file = "hash-tables/src/main/java/by/example/postnummer.csv";
        Zip zip = new Zip(file);

        //When storing with data[i++] String code:
        //								Binary search		 Linear search
        //Time to find the first element: 1708.00			41.00
        //Time to find the last element: 1625.00			19209.00

        //When storing with data[i++] Integer code:
        //								Binary search		 Linear search
        //Time to find the first element: 208.00			125.00
        //Time to find the last element: 166.00			10416.00

        //When storing with data[code]:
        //								Binary search		 Linear search
        //Time to find the first element: 167.00			4292.00
        //Time to find the last element: 166.00			49209.00
        System.out.println(zip.binary(11115));
        System.out.println(zip.linear(11115));
    }
}