package by.example;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueueFastRemove {
    private final List<Integer> queue;

    public PriorityQueueFastRemove() {
        queue = new ArrayList<>();
    }

    public void add(int value) {
        int minValue = queue.get(0);
        int minIndex = 0;
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) < minValue) {
                minIndex = i;
                minValue = queue.get(i);
            }
        }
        queue.add(minIndex,value);
    }

    public Integer remove() {
        if (isEmpty()) {
            int minIndex = 0;
            return queue.remove(minIndex);
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
