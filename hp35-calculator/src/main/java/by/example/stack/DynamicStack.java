package by.example.stack;

import java.util.EmptyStackException;

/**
 * A fixed sized stack where the size is given when the stack is created. The stack should allocate an array of this
 * size and keep track of a stack pointer (an index).
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
		var newLength = array.length;
		if (pointer >= newLength) {
			resize(2 * newLength);
		}
		array[pointer] = value;
		pointer++;
	}

	@Override
	public int pop() {
       if (pointer <= 0) {
           throw new EmptyStackException();
       }
		int value = array[--pointer];
		if (pointer > 0 && pointer == array.length / 4) {
			resize(array.length / 2);
		}
		return value;
	}

	private void resize(int capacity) {
		int[] copy = new int[capacity];
       var copySize = Math.min(capacity, array.length);
       for (int i = 0; i < copySize; i++) {
			copy[i] = array[i];
		}
		array = copy;
	}
}
