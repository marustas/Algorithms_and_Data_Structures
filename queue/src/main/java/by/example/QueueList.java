package by.example;

public class QueueList<T> {
    Node first;
    Node last;

    private class Node {
        T item;
        Node next;

        private Node(T item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public QueueList() {
        first = last = null;
    }

    public void add(T item) {
        Node newNode = new Node(item, null);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public T remove() {
        if (first == null) {
            return null;
        }
        T item = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}