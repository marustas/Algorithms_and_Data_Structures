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