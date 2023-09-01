package by.example.stack;

import java.util.EmptyStackException;

/**
 * A fixed sized stack where the size is given when the stack is created.
 * The stack should allocate an array of this size and keep track of a stack pointer (an index).
 */
public class StaticStack implements Stack {
    private final int[] array;
    private int pointer;

    public StaticStack(int size) {
        array = new int[size];
        pointer = -1;
    }

    @Override
    public void push(int value) {
        pointer++;
        if (pointer >= array.length) {
            throw new StackOverflowError("Stack is full");
        }
        array[pointer] = value;
    }

    @Override
    public int pop() {
        if (pointer == -1) {
            throw new EmptyStackException();
        }
        int value = array[pointer];
        pointer--;
        return value;
    }
}
