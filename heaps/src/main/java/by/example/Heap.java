package by.example;

public class Heap {
    Node root;


    private static class Node {
        Node left, right;
        int priority;
        int rightLength = 0;
        int leftLength = 0;

        private Node(int priority) {
            this.left = null;
            this.right = null;
            this.priority = priority;
        }

        private Node remove() {
            if (right == null) {
                return left;
            }
            if (left == null) {
                return right;
            }
            if (left.priority < right.priority) {
                this.priority = left.priority;
                left = left.remove();
//same as return this. return left;
            } else {
                this.priority = right.priority;
                right = right.remove();
//same as return this return right;
            }
            return this;
        }

        private void add(int pr) {
            if (pr < priority) {
                int temp = priority;
                priority = pr;
                pr = temp;
            }
            if (rightLength >= leftLength) {
                if (left != null) {
                    left.add(pr);
                } else {
                    left = new Node(pr);
                }
                leftLength++;
            } else {
                if (right != null) {
                    right.add(pr);
                } else {
                    right = new Node(pr);
                }
                rightLength++;
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

    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.enqueue(12);
        heap.enqueue(16);
        heap.enqueue(8);
        heap.enqueue(9);
        heap.enqueue(1);
        heap.enqueue(22);
        System.out.println(heap.dequeue());
        System.out.println(heap.dequeue());
        System.out.println(heap.dequeue());
        System.out.println(heap.dequeue());
        System.out.println(heap.dequeue());
        System.out.println(heap.dequeue());
    }
}
