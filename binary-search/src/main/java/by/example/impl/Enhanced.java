package by.example.impl;

public class Enhanced {
    public static boolean search(int[] arr1, int[] arr2) {
        int ptr1 = 0; // Pointer for the first array
        int ptr2 = 0; // Pointer for the second array

        boolean foundDuplicates = false;

        while (ptr1 < arr1.length && ptr2 < arr2.length) {
            if (arr1[ptr1] < arr2[ptr2]) {
                ptr1++; // Move forward in the first array
            } else if (arr1[ptr1] > arr2[ptr2]) {
                ptr2++; // Move forward in the second array
            } else {
                // Found a duplicate
                foundDuplicates = true;
                ptr1++;
                ptr2++;
            }
        }

        return foundDuplicates;
    }
}

