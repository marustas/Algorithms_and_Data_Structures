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

    public boolean linear(String zip) {
        Integer zipCode = Integer.valueOf(zip.replaceAll(" ", ""));
        for (Node datum : data) {
            if (datum.code.equals(zipCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean binary(String zip) {
        Integer zipCode = Integer.valueOf(zip.replaceAll(" ", ""));
        int start = 0;
        int end = data.length - 1;
        while (true) {
            int mid = (start + end) / 2;
            if (data[mid].code.equals(zipCode)) {
                return true;
            }
            if (data[mid].code > (zipCode)) {
                end = mid - 1;
                continue;
            }
            if (data[mid].code < (zipCode)) {
                start = mid + 1;
                continue;
            }
            break;
        }
        return false;
    }

    public Zip(String file) {
        data = new Node[10000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                data[i++] = new Node(code, row[1], Integer.valueOf(row[2]));
            }
            max = i - 1;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public static void main(String[] args) {
        String file = "hash-tables/src/main/java/by/example/postnummer.csv";
        Zip zip = new Zip(file);
        System.out.println(zip.binary("111 15"));
        System.out.println(zip.linear("984 99"));
    }
}