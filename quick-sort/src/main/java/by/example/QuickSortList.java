package by.example;

public class QuickSortList {
    static LinkedList smallNodes = new LinkedList();
    static LinkedList largeNodes = new LinkedList();

    public static void sort(LinkedList list) {
        sortRecursive(list);
        smallNodes.append(largeNodes);
    }

    private static void sortRecursive(LinkedList list) {
        partition(list);
        sortRecursive(smallNodes);
        sortRecursive(largeNodes);
    }

    private static Node partition(LinkedList list) {

        Node pivot = list.first;
        Node current = list.first;

        while (current != null) {
            while (current.value <= pivot.value) {
                smallNodes.add(current.value);
                current = smallNodes.last.next;
            }
            while (current.value > pivot.value) {
                largeNodes.add(current.value);
                current = largeNodes.last.next;
            }
            current = current.next;
        }
        return pivot;
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
            Node newCell;
            if (first == null) {
                last = first = new Node(item, null);
            } else {
                newCell = new Node(item, null);
                last.next = newCell;
                last = newCell;
            }
        }

        public void append(LinkedList anotherList) {
            last.next = anotherList.first;
            anotherList.first = null;
        }

    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(6);
        list.add(4);
        list.add(7);
        sort(list);
        System.out.println();
    }
}
