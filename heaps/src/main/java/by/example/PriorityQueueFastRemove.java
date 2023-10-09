package by.example;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueueFastRemove {
    private final List<Integer> queue;

    public PriorityQueueFastRemove() {
        queue = new ArrayList<>();
    }

    public void add(int value) {
        int index = 0;
        while (index < queue.size() && value > queue.get(index)) {
            index++;
        }
        queue.add(index, value);
    }

    public Integer remove() {
        int start = 0;
        if (isEmpty()) {
            return queue.remove(start);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        PriorityQueueFastRemove pq = new PriorityQueueFastRemove();
        pq.add(3);
        pq.add(1);
        pq.add(4);
        pq.add(2);

        while (pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }
}
