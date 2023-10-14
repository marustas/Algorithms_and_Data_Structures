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

    public boolean linear(Integer zip) {
        for (Node datum : data) {
            if (datum.code.equals(zip)) {
                return true;
            }
        }
        return false;
    }

    public boolean binary(Integer zip) {
        int length = data.length;
        int start = 0;
        int end = length - 1;
        while (true) {
            int mid = (start + end) / 2;
            if (data[mid].code.equals(zip)) {
                return true;
            }
            if (data[mid].code > (zip)) {
                end = mid - 1;
                continue;
            }
            if (data[mid].code < (zip)) {
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
        Zip zip = new Zip("postnummer");
        System.out.println(zip.binary(11115));
        System.out.println(zip.linear(11115));
    }
}