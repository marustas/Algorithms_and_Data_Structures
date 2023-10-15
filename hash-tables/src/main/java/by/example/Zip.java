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

    public boolean linear(String zip) {
        Integer zipCode = Integer.valueOf(zip.replaceAll(" ", ""));
        for (Node datum : data) {
            if (datum != null && datum.code.equals(zipCode)) {
                return true;
            }
        }
        return false;
    }

    public boolean binary(String zip) {
        Integer zipCode = Integer.valueOf(zip.replaceAll("\\s", ""));
        int start = 0;
        int end = data.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (data[mid] != null) {
                if (data[mid].code.equals(zipCode)) {
                    return true;
                }
                if (data[mid].code > zipCode) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (mid > zipCode) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return false;
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
                i++;
            }
            max = i;
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    private Integer hash(Integer code) {
        return code % 100;
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
        int[] modulos = {10_000, 20_000, 13513, 13600, 14000};
        for (int modulo : modulos) {
            zip.collisions(modulo);
        }
        System.out.println(zip.binary("984 99"));
        System.out.println(zip.linear("984 99"));
    }
}