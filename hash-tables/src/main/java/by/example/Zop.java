package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class Zop {
    Node[] data;
    int max;

    public static class Node {
        String code;
        String name;
        Integer pop;

        private Node(String code, String name, Integer pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    public String linear(String zip) {
        for (Node datum : data) {
            if (datum.code.equals(zip)) {
                return datum.name;
            }
        }
        return null;
    }

    public String binary(String zip) {
        Integer zipCode = Integer.valueOf(zip.replaceAll("\\s", ""));
        int start = 0;
        int end = max;
        while (start <= end) {
            int mid = (start + end) / 2;
            Integer midCode = Integer.valueOf(data[mid].code.replaceAll("\\s", ""));
            if (midCode.equals(zipCode)) {
                return data[mid].name;
            }
            if (midCode > zipCode) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return null;
    }

    public Zop(String file) {
        data = new Node[100_000];
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                String code = row[0];
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
        Zop zop = new Zop(file);
// converting string to integer takes more time than searching simply for an integer.
        System.out.println(zop.linear("111 15"));
        System.out.println(zop.binary("111 15"));

        System.out.println(zop.linear("984 99"));
        System.out.println(zop.binary("984 99"));
    }
}
