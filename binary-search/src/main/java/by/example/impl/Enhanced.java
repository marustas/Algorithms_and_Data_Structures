package by.example.impl;

import by.example.DuplicateSearch;

public class Enhanced implements DuplicateSearch {

    boolean debug;

    public Enhanced debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    @Override
    public boolean search(int[] where, int[] what) {
        int ptr1 = 0; // Pointer for the first array
        int ptr2 = 0; // Pointer for the second array

        boolean foundDuplicates = false;
        int counter = 0;
        while (ptr1 < where.length && ptr2 < what.length) {
            if (where[ptr1] < what[ptr2]) {
                ptr1++; // Move forward in the first array
            } else if (where[ptr1] > what[ptr2]) {
                ptr2++; // Move forward in the second array
            } else {
                // Found a duplicate
                foundDuplicates = true;
                counter++;
                ptr1++;
                ptr2++;
            }
        }
        if(debug) {
            System.out.printf("%d DUPLICATES found%n", counter);
        }
        return foundDuplicates;
    }
}

