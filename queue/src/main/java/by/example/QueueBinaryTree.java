package by.example;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueBinaryTree implements Iterable<Integer> {
    public static class TreeIterator implements Iterator<Integer> {
        private final QueueList<Node> queue;

        public TreeIterator() {
            queue = new QueueList<>();
            if (root != null) {
                enqueue(root);
            }
        }

        private void enqueue(Node current) {
            if (current != null) {
                queue.add(current);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Node current = queue.remove();
            enqueue(current.left);
            enqueue(current.right);
            return current.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }

    public static class Node {
        public int key;
        public int value;
        public Node left, right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    //If root is static, the debug can't see the nodes in the tree
    static Node root;

    public QueueBinaryTree() {
        root = null;
    }

    public Integer lookup(Integer key) {
        return lookupRecursive(root, key);
    }

    private Integer lookupRecursive(Node current, Integer key) {
        if (current == null) {
            return null;
        }
        if (key.equals(current.key)) {
            return current.value;
        } else if (key < current.key) {
            return lookupRecursive(current.left, key);
        } else {
            return lookupRecursive(current.right, key);
        }
    }

    public void add(Integer key, Integer value) {
        root = addRecursive(root, key, value);
    }

    private Node addRecursive(Node current, Integer key, Integer value) {
        if (current == null) {
            return new Node(key, value);
        }

        if (key.equals(current.key)) {
            current.value = value;
        } else if (key < current.key) {
            current.left = addRecursive(current.left, key, value);
        } else {
            current.right = addRecursive(current.right, key, value);
        }
        return current;
    }
}

