package by.example;

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
//same as return this. return left;
            } else {
                this.priority = right.priority;
                right = right.remove();
//same as return this return right;
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

    public static void main(String[] args) {
        ListHeap heap = new ListHeap();
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
