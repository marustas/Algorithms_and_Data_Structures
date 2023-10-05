package by.example;

public class QuickSortList {

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

        public void sort() {
            if (this.first == null || this.first == this.last) {
                return;
            }

            LinkedList smallNodes = new LinkedList();
            LinkedList largeNodes = new LinkedList();

            Node pivot = this.first;
            Node current = pivot.next;
            pivot.next = null;
            this.last = pivot;

            while (current != null) {
                Node next = current.next;
                if (current.value < pivot.value) {
                    smallNodes.move(current);
                } else {
                    largeNodes.move(current);
                }
                current = next;
            }
            System.out.println();
            smallNodes.sort();
            largeNodes.sort();

            this.prepend(smallNodes);
            this.append(largeNodes);
        }

        public void add(int item) {
            Node newCell = new Node(item, first);
            if (last == null)
                last = newCell;
            first = newCell;
        }

        public void move(Node node) {
            if (this.last == null)
                this.last = node;
            node.next = this.first;
            this.first = node;
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
        LinkedList list = new LinkedList();
        list.add(6);
        list.add(4);
        list.add(7);
        list.add(1);
        list.add(2);
        list.add(9);
        list.add(8);
        list.add(5);
        list.sort();

        Node current = list.first;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
    }
}