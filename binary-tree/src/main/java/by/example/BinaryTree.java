package by.example;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BinaryTree<K extends Comparable<K>, V> implements Iterable<K> {
	private Node<K, V> root;

/*	@Override
	public Iterator<V> iterator() {
		return new TreeIterator<V>();
	}*/

	@Override
	public Iterator<K> iterator() {
		return new TreeIterator<K>();;
	}

	@Override
	public void forEach(Consumer<? super K> action) {
		Iterable.super.forEach(action);
	}

	@Override
	public Spliterator<K> spliterator() {
		return Iterable.super.spliterator();
	}

	public void add(K key, V value) {
		root = addNode(root, key, value);
	}

	private Node<K, V> addNode(Node<K, V> current, K key, V value) {
		if (current == null) {
			return new Node<K, V>(key, value);
		}

		int cmp = key.compareTo(current.getKey());
		if (cmp < 0) {
			current.setLeft(addNode(current.getLeft(), key, value));
		} else if (cmp > 0) {
			current.setRight((addNode(current.getRight(), key, value));
		} else {
			// Key already exists, update the value
			current.setValue(value);
		}

		return current;
	}

	public V lookup(K key) {
		return findValue(root, key);
	}

	private V findValue(Node<K, V> current, K key) {
		if (current == null) {
			return null;
		}

		int cmp = key.compareTo(current.getKey());
		if (cmp < 0) {
			return findValue(current.getLeft(), key);
		} else if (cmp > 0) {
			return findValue(current.getRight(), key);
		} else {
			return current.getValue();
		}
	}
}
