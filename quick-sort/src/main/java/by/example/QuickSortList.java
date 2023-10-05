package by.example;
public class QuickSortList {
    public void sort(LinkedList list) {
        if (list.first == null || list.first == list.last) {
            return;
        }

        LinkedList smallNodes = new LinkedList();
        LinkedList largeNodes = new LinkedList();

        Node pivot = list.first;
        list.first = pivot.next;
        pivot.next = null;

        Node current = list.first;

        while (current != null) {
            Node next = current.next;
            if (current.value <= pivot.value) {
                current.next = smallNodes.first;
                smallNodes.first = current;
            } else {
                current.next = largeNodes.first;
                largeNodes.first = current;
            }
            current = next;
        }

        sort(smallNodes);
        sort(largeNodes);

        list.first = append(smallNodes.first, pivot, largeNodes.first);
        list.last = findLast(list.first);
    }

    private Node append(Node small, Node pivot, Node large) {
        if (small == null) {
            if (large == null) {
                return pivot;
            } else {
                return append(pivot, large);
            }
        } else {
            Node lastSmall = findLast(small);
            lastSmall.next = pivot;
            if (large == null) {
                return small;
            } else {
                lastSmall = findLast(large);
                lastSmall.next = null;
                return append(small, large);
            }
        }
    }

    private Node append(Node first, Node second) {
        if (first == null) {
            return second;
        }

        Node current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = second;
        return first;
    }

    private Node findLast(Node node) {
        while (node != null && node.next != null) {
            node = node.next;
        }
        return node;
    }

    private static class Node {
        int value;
        Node next;

        Node(int element, Node tl) {
            value = element;
            next = tl;
        }
    }

    private static class LinkedList {
        private Node first;
        private Node last;

        public void add(int item) {
            Node newCell = new Node(item, null);
            if (first == null) {
                last = first = newCell;
            } else {
                last.next = newCell;
                last = newCell;
            }
        }
    }

    public static void main(String[] args) {
        QuickSortList quickSort = new QuickSortList();
        LinkedList list = new LinkedList();
        list.add(6);
        list.add(4);
        list.add(7);
        list.add(2);
        list.add(9);
        quickSort.sort(list);

        Node current = list.first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}
