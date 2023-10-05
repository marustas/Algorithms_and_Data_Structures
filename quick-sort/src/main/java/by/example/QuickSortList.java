package by.example;

import java.util.Random;

public class QuickSortList {

    private static class Node {
        int value;
        Node next;

        Node(int element, Node tl) {
            this.value = element;
            this.next = tl;
        }
    }

    private static class LinkedList {
        private Node first;
        private Node last;

        public void sort() {
            if (this.first == null || this.first == this.last) {
                return;
            }

            LinkedList smallNodes = new LinkedList();
            LinkedList largeNodes = new LinkedList();

            Node pivot = first;
            Node current = pivot.next;
            pivot.next = null;
            last = pivot;
            int p = pivot.value;
            while (current != null) {
                Node next = current.next;
                if (current.value <= p) {
                    smallNodes.move(current);
                } else {
                    largeNodes.move(current);
                }
                current = next;
            }
            smallNodes.sort();
            largeNodes.sort();

            prepend(smallNodes);
            append(largeNodes);
        }

        public void add(int item) {
            Node newCell = new Node(item, first);
            if (last == null)
                last = newCell;
            first = newCell;
        }

        private void move(Node node) {
            if (last == null)
                last = node;
            node.next = first;
            first = node;
        }

        public void prepend(LinkedList large) {
            if (large != null) {
                if (large.last != null)
                    large.last.next = first;
                if (last == null)
                    last = large.last;
                if (large.first != null)
                    first = large.first;
            }
        }

        public void append(LinkedList small) {
            if (small != null) {
                if (last != null)
                    last.next = small.first;
                else
                    first = small.first;
                if (small.last != null)
                    last = small.last;
            }
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        LinkedList list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }
        list.sort();

        Node current = list.first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}