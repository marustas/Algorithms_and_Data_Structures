package by.example;

public class PriorityQueueFastAdd {
    Node first;

    public PriorityQueueFastAdd() {
        first = null;
    }

    public void add(int item) {
        Node newNode = new Node(item, null);
        if (first != null) {
            newNode.next = first;
        }
        first = newNode;
    }

    public int remove() {
        if (isEmpty()) {
            return -1;
        }
        Node current = first;
        int minPriority = first.priority;
        Node previous = first;
        Node candidate = first;
        while (current.next != null) {
            if (minPriority > current.next.priority) {
                previous = current;
                candidate = current.next;
                minPriority = candidate.priority;
            }
            current = current.next;
        }
        if (previous == candidate) {
            first = candidate.next;
        } else {
            previous.next = candidate.next;
        }
        return candidate.priority;
    }

    public boolean isEmpty() {
        return first == null;
    }

    private static class Node {
        int priority;
        Node next;

        private Node(int item, Node list) {
            this.priority = item;
            this.next = list;
        }
    }

    public static void main(String[] args) {
        PriorityQueueFastAdd pq = new PriorityQueueFastAdd();
        pq.add(3);
        pq.add(1);
        pq.add(4);
        pq.add(2);
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println();
    }
}
