package by.example.impl;

import by.example.DuplicateSearch;
import by.example.KeySearch;

public class Linear implements KeySearch, DuplicateSearch {


    boolean debug;

    public Linear debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    @Override
    public boolean search(int[] array, int key) {
        for (int i : array) {
            if (i == key) {
                return true;
            }
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
