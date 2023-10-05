package by.example;

import java.util.Random;

public class QuickSortList {

    public static class Node {
        int value;
        Node next;

        Node(int element, Node tl) {
            this.value = element;
            this.next = tl;
        }
    }

    public void sort(LinkedList list) {
        if (list.first == null || list.first == list.last) {
            return;
        }

        LinkedList smallNodes = new LinkedList();
        LinkedList largeNodes = new LinkedList();

        Node pivot = list.first;
        Node current = pivot.next;
        pivot.next = null;
        list.last = pivot;
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
        sort(smallNodes);
        sort(largeNodes);

        prepend(list, smallNodes);
        append(list, largeNodes);
    }

    public void prepend(LinkedList list, LinkedList small) {
        if (small != null) {
            if (small.last != null)
                small.last.next = list.first;
            if (list.last == null)
                list.last = small.last;
            if (small.first != null)
                list.first = small.first;
        }
    }

    public void append(LinkedList list, LinkedList large) {
        if (large != null) {
            if (list.last != null)
                list.last.next = large.first;
            else
                list.first = large.first;
            if (large.last != null)
                list.last = large.last;
        }
    }

    public static class LinkedList {
        private Node first;
        private Node last;

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

    }
}