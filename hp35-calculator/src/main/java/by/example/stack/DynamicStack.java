package by.example.stack;

import java.util.Arrays;

public class DynamicStack implements Stack {
    private int[] array = new int[0];

    @Override
    public void push(int value) {
        resize(array.length + 1);
        array[array.length - 1] = value;
    }

    @Override
    public int pop() {
        if (array.length == 0) {
            throw new RuntimeException("Stack underflow!");
        }
        int lastItem = array[array.length - 1];
        resize(array.length - 1);
        return lastItem;
    }

    private void resize(int newCapacity) {
        int[] tempArray = new int[newCapacity];
        var length = Math.min(array.length, newCapacity);
        for (int i = 0; i < length; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
        //array = Arrays.copyOf(array, newCapacity);
    }

}