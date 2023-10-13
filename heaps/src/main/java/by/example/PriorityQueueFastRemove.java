package by.example;
public class PriorityQueueFastRemove {
    Node first;

    public PriorityQueueFastRemove() {
        first = null;
    }

    public void add(int item) {
        Node newNode = new Node(item, null);
        if (first == null || first.priority >= newNode.priority) {
            newNode.next = first;
            first = newNode;
        } else {
            Node current = first;
            while (current.next != null && current.next.priority < newNode.priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public int remove() {
        if (isEmpty()) {
            return -1;
        }
        int topPriority = first.priority;
        first = first.next;
        return topPriority;
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
        PriorityQueueFastRemove pq = new PriorityQueueFastRemove();
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
