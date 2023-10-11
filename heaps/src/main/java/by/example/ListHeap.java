package by.example;

import java.util.Random;

public class ListHeap {
    Node root;


    private static class Node {
        Node left, right;
        int priority;
        int size = 1;

        private Node(int priority) {
            this.left = null;
            this.right = null;
            this.priority = priority;
        }

        private Node remove() {
            size -= 1;
            if (right == null) {
                return left;
            }
            if (left == null) {
                return right;
            }
            if (left.priority < right.priority) {
                this.priority = left.priority;
                left = left.remove();
            } else {
                this.priority = right.priority;
                right = right.remove();
            }
            return this;
        }

        private void add(int pr) {
            size += 1;
            if (pr < priority) {
                int temp = priority;
                priority = pr;
                pr = temp;
            }
            if (right != null) {
                if (left != null) {
                    if (left.size < right.size) {
                        left.add(pr);
                    } else {
                        right.add(pr);
                    }
                } else {
                    left = new Node(pr);
                }
            } else {
                right = new Node(pr);
            }
        }
    }

    public void enqueue(int p) {
        if (root == null) {
            root = new Node(p);
        } else {
            root.add(p);
        }
    }

    public int dequeue() {
        if (root == null) {
            return -1;
        }
        int p = root.priority;
        root = root.remove();
        return p;
    }

    public int push(int increment) {
        int depth = 0;
        if (root != null) {
            root.priority += increment;
            Node current = root;
            while (true) {
                depth++;
                Node left = current.left;
                Node right = current.right;
                Node smallestChild = null;

                if (left != null && (right == null || left.priority < right.priority)) {
                    smallestChild = left;
                } else if (right != null) {
                    smallestChild = right;
                }

                if (smallestChild != null && current.priority > smallestChild.priority) {
                    int temp = current.priority;
                    current.priority = smallestChild.priority;
                    smallestChild.priority = temp;
                    current = smallestChild;
                } else {
                    break;
                }
            }
        }
        return depth;
    }


    public static void main(String[] args) {
        ListHeap listHeap = new ListHeap();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            listHeap.enqueue(random.nextInt(100));
        }
        int i = 0;
        while (i < 10) {
            System.out.println(listHeap.dequeue());
            i++;
        }
    }
}
