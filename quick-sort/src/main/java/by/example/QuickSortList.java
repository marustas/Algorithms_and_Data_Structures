package by.example;

public class QuickSortList {
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

        while (current != null) {
            Node next = current.next;
            if (current.value <= pivot.value) {
                smallNodes.move(current);
            } else {
                largeNodes.move(current);
            }
            current = next;
        }

        sort(smallNodes);
        sort(largeNodes);

        list.prepend(smallNodes);
        list.append(largeNodes);
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
            Node newCell = new Node(item, first);
            if (last == null)
                last = newCell;
            first = newCell;
        }

        public void move(Node node) {
            if (last == null)
                last = node;
            node.next = first;
            first = node;
        }

        public void prepend(LinkedList anotherList) {
            if (anotherList != null) {
                if (anotherList.last != null) {
                    anotherList.last.next = first;
                }
                if (last == null) {
                    last = anotherList.last;
                }
                if (anotherList.first != null) {
                    first = anotherList.first;
                }
            }
        }

        public void append(LinkedList anotherList) {
            if (anotherList != null) {
                if (last != null) {
                    last.next = anotherList.first;
                } else {
                    first = anotherList.first;
                }
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
        list.add(8);
        list.add(5);
        quickSort.sort(list);

        Node current = list.first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}
