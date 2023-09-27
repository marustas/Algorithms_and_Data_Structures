package by.example;

public class BinaryTree {
    public static class Node {
        public int key;
        public int value;
        public Node left, right;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
        }
    }

    Node root;

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

