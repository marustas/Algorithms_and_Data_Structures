package by.example.stack;

import java.util.EmptyStackException;

/**
 * A fixed sized stack where the size is given when the stack is created.
 * The stack should allocate an array of this size and keep track of a stack pointer (an index).
 */
public class DynamicStack implements Stack {
    private int[] array;
    private int pointer;

    public DynamicStack() {
        array = new int[4];
        pointer = -1;
    }

    @Override
    public void push(int value) {
        pointer++;
        if (pointer >= array.length) {
            resize(2*array.length);
        }
        array[pointer] = value;
    }

    @Override
    public int pop() {
        int value = array[pointer];
        if (pointer == -1) {
            throw new EmptyStackException();
        }
        if (pointer > 0 && pointer == array.length/4) resize(array.length/2);
        pointer--;
        return value;
    }

    private void resize(int capacity) {
        assert capacity >= pointer;

        // textbook implementation
        int[] copy = new int[capacity];
        for (int i = 0; i < pointer; i++) {
            copy[i] = array[i];
        }
        array = copy;

        // alternative implementation
        // a = java.util.Arrays.copyOf(a, capacity);
    }
}
