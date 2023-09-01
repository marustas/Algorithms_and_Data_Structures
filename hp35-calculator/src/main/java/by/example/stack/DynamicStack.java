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
        pointer = 0;
    }

    @Override
    public void push(int value) {
        if (pointer >= array.length) {
            resize(2*array.length);
        }
        array[pointer] = value;
        pointer++;
    }

    @Override
    public int pop() {
        int value = array[pointer];
        if (pointer <= 0) {
            throw new EmptyStackException();
        }
        pointer--;
        if (pointer > 0 && pointer == array.length/4) resize(array.length/2);
        return value;
    }

    private void resize(int capacity) {
        assert capacity >= pointer;
        int[] copy = new int[capacity];
        for (int i = 0; i < pointer; i++) {
            copy[i] = array[i];
        }
        array = copy;
    }
}
