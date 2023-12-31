package by.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Objects;

public class HashBuckets {
    Node[] buckets;

    private static class Node {
        Integer code;
        String name;
        Integer pop;
        Node next;

        private Node(Integer code, String name, Integer pop) {
            this.code = code;
            this.name = name;
            this.pop = pop;
            this.next = null;
        }
    }

    private void put(Integer index, Node node) {
        if (buckets[index] == null) {
            buckets[index] = node;
        } else {
            Node current = buckets[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
    }

    public int lookup(Integer zip) {
        int length = 0;
        int bucket = hash(zip);
        if (buckets[bucket] != null) {
            Node current = buckets[bucket];
            while (current != null) {
                if (Objects.equals(current.code, zip)) {
                    return length;
                }
                length++;
                current = current.next;
            }
        }
        return -1;
    }

    public HashBuckets(String file) {
        buckets = new Node[13513];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Integer code = Integer.valueOf(row[0].replaceAll("\\s", ""));
                String name = row[1];
                Integer pop = Integer.valueOf(row[2]);
                Integer index = hash(code);
                Node entry = new Node(code, name, pop);
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
