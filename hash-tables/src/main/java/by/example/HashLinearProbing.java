package by.example;

import java.io.BufferedReader;
import java.io.FileReader;

public class HashLinearProbing {
    Node[] buckets;

    private static class Node {
        Integer code;
        String name;
        Integer pop;

        private Node(Integer code, String name, Integer pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
        }
    }

    private void put(Integer index, Node node) {
        buckets[index] = node;
    }

    public int lookup(Integer zip) {
        int index = hash(zip);
        int length = 0;
        while (buckets[index] != null) {
            if (buckets[index].code.equals(zip)) {
                return length;
            }
            length++;
            index++;
        }
        return -1;
    }

    public HashLinearProbing(String file) {
        buckets = new Node[20000];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                String name = row[1];
                Integer pop = Integer.valueOf(row[2]);
                Integer index = hash(code);
                Node entry = new Node(code, name, pop);
                while (buckets[index] != null) {
                    index++;
                }
                put(index, entry);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private Integer hash(Integer key) {
        return key % 13513;
    }
}

