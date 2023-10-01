package by.example;

public class Queue {
    Node head;

    private static class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node list) {
            this.item = item;
            this.next = list;
        }
    }

    public Queue() {
        LinkedList list = new LinkedList(0);
    }

    public void add(Integer item) {
        if (head == null)
            head = new Node(item, null);

        Node newNode = new Node(item, head);
        head = newNode;
    }

    public Integer remove() {
        return 0;
    }
}