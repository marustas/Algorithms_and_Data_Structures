package by.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BinaryTree implements Iterable<Integer> {
    public static class TreeIterator implements Iterator<Integer> {
        private final Stack<Node> stack;

        public TreeIterator() {
            stack = new Stack<>();
            moveLeft(root);
//Alternative implementation
//            Node current = root;
//            while (current != null) {
//                stack.push(current);
//                current = current.left;
//            }
        }

        private void moveLeft(Node current) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Node current = stack.pop();
            if (current.right != null)
                moveLeft(current.right);
//Alternative implementation:
//            Node next = current.right;
//
//            if (next != null)
//                while (next != null) {
//                    stack.push(next);
//                    next = next.left;
//                }
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

        private int maxDepth() {
            int lm = 0;
            int rm = 0;
            if (left != null)
                lm = left.maxDepth();

            if (right != null)
                rm = right.maxDepth();
            return Math.max(lm + 1, rm + 1);
        }
    }

    public int maxDepth() {
        if (root != null) {
            return root.maxDepth();
        } else {
            return 0;
        }
    }

    //If root is static, the debug can't see the nodes in the tree
    static Node root;

    public BinaryTree() {
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

