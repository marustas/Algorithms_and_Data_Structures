package by.example.stack;

import java.util.EmptyStackException;

public class Stack {
    private int[] arr;
    private int index;

    public Stack() {
        index = -1;
        arr = new int[1000];
    }

    public void push(int element) {
        if (isFull()) {
            throw new StackOverflowError("Stack is full");
        }
        arr[++index] = element;
    }
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return arr[index--];
    }

    public boolean isEmpty() {
        if (index == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (index + 1 >= arr.length) {
            return true;
        }
        return false;
    }

}
