package by.example;

public class Queue {
    Node first;
    Node last;

    private static class Node {
        QueueBinaryTree.Node item;
        Node next;

        private Node(QueueBinaryTree.Node item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public Queue() {
        first = last = null;
    }

    public void add(QueueBinaryTree.Node item) {
        Node newNode = new Node(item, null);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public QueueBinaryTree.Node remove() {
        if (first == null) {
            return null;
        } else if (first.equals(last)) {
            Node tempNode = first;
            last = null;
            first = null;
            return tempNode.item;
        } else {
            Node tempNode = first;
            first = first.next;
            return tempNode.item;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }
}