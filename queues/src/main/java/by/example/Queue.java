package by.example;

public class Queue {
    QueueNode first;
    QueueNode last;

    private static class QueueNode {
        Integer item;
        QueueNode next;

        private QueueNode(Integer item, QueueNode list) {
            this.item = item;
            this.next = list;
        }
    }

    public Queue() {
        first = last = null;
    }

    public void add(Integer item) {
        QueueNode newNode = new QueueNode(item, null);
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
            QueueNode tempNode = first;
            first = first.next;
            return tempNode.item;
        }
    }
}