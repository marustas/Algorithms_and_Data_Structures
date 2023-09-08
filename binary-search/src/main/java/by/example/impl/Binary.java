package by.example.impl;

import by.example.DuplicateSearch;
import by.example.KeySearch;

public class Binary implements KeySearch, DuplicateSearch {

    boolean debug;

    public Binary debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    @Override
    public boolean search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;
        while (true) {
            int index = (first + last) / 2;
            if (array[index] == key) {
                return true;
            }

            if (array[index] < key && index < last) {
                first = index + 1;
                continue;
            }

            if (array[index] > key && index > first) {
                last = index - 1;
                continue;
            }
            break;
        }
        return false;
    }

    @Override
    public boolean search(int[] where, int[] what) {
        boolean foundDuplicates = false;
        int counter = 0;
        for (int key : what) {
            if (search(where, key)) {
                foundDuplicates = true;
                counter++;
            }
        }
        if(debug) {
            System.out.printf("%d DUPLICATES found%n", counter);
        }
        return foundDuplicates;
    }
}
