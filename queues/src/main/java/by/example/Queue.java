package by.example;

public class Queue {
    Node first;
    Node last;

    private static class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public Queue() {
        first = last = null;
    }

    public void add(Integer item) {
        Node newNode = new Node(item, null);
        if (last == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
    }

    public Integer remove() {
        if (first == null) {
            return null;
        } else {
            Node tempNode = first;
            first = first.next;
            return tempNode.item;
        }
    }
}