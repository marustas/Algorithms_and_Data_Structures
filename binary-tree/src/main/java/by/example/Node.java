package by.example;

class Node<K extends Comparable<K>, V> {
	private K key;
	private V value;
	private Node<K, V> left; // NULL by default
	private Node<K, V> right; // NULL by default

	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

	public void print() {
		if (left != null) {
			left.print();
		}
		System.out.println(" key: " + key + "\tvalue: " + value);
		if (right != null) {
			right.print();
		}
	}
}
