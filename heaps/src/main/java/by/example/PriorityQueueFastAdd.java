package by.example;

import java.util.ArrayList;
import java.util.List;

//Unsorted list, add to the first, remove by searching for smallest element
// More or less better, because we can't remove more than we added
public class PriorityQueueFastAdd {
    private final List<Integer> queue;

    public PriorityQueueFastAdd() {
        queue = new ArrayList<>();
    }

    public void add(int value) {
        queue.add(value);
    }

    public Integer remove() {
        if (isEmpty()) {
            int minIndex = 0;
            int minValue = queue.get(0);

            for (int i = 1; i < queue.size(); i++) {
                if (queue.get(i) < minValue) {
                    minIndex = i;
                    minValue = queue.get(i);
                }
            }

            return queue.remove(minIndex);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        PriorityQueueFastAdd pq = new PriorityQueueFastAdd();
        pq.add(3);
        pq.add(1);
        pq.add(4);
        pq.add(2);

        while (pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }
}
