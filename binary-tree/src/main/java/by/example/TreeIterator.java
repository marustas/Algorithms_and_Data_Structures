package by.example;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeIterator<K extends Comparable<K>, V> implements Iterator<K> {
	private final Deque<Node<K, V>> stack;

	private Node<K, V> next;

	public TreeIterator() {
		stack = new ArrayDeque<>();
	}

	@Override
	public boolean hasNext() {
		return !stack.isEmpty();
	}

	@Override
	public V next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		Node<K, V> current = stack.pop();
		if (current.getRight() != null) {
			moveLeft(current.getRight());
		}

		return current.getValue();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	private void moveLeft(Node<K, V> current) {
		while (current != null) {
			stack.push(current);
			current = current.getLeft();
		}
	}

}