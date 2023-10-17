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
            }
            max = i;
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
        int tries = 10_000;
        double minBinary = Double.POSITIVE_INFINITY;
        double minLinear = Double.POSITIVE_INFINITY;
        double minBinary2 = Double.POSITIVE_INFINITY;
        double minLinear2 = Double.POSITIVE_INFINITY;
        for (int i = 0; i < tries; i++) {
            double binaryStart = System.nanoTime();
            zip.binary("111 15");
            double binaryTime = System.nanoTime() - binaryStart;
            if (minBinary > binaryTime) {
                minBinary = binaryTime;
            }

            double linearStart = System.nanoTime();
            zip.linear("111 15");
            double linearTime = System.nanoTime() - linearStart;
            if (minLinear > linearTime) {
                minLinear = linearTime;
            }

            double binaryStart2 = System.nanoTime();
            zip.binary("984 99");
            double binaryTime2 = System.nanoTime() - binaryStart2;
            if (minBinary2 > binaryTime2) {
                minBinary2 = binaryTime2;
            }

            double linearStart2 = System.nanoTime();
            zip.linear("984 99");
            double linearTime2 = System.nanoTime() - linearStart2;
            if (minLinear2 > linearTime2) {
                minLinear2 = linearTime2;
            }
        }
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
        System.out.println("\t\t\t\t\t\t\t\tBinary search\t\t Linear search");
        System.out.printf("Time to find the first element: %.2f\t\t\t%.2f\n", minBinary, minLinear);
        System.out.printf("Time to find the last element: %.2f\t\t\t%.2f\n", minBinary2, minLinear2);
        System.out.println(zip.binary("984 99"));
        System.out.println(zip.linear("984 99"));
    }
}