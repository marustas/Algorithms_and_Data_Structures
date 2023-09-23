package by.example;

public class DoubleLinkedList implements List {
    private Cell head;
    private final Cell[] data;

    private int size = 0;

    public DoubleLinkedList(int n) {
        data = new Cell[n];
    }

    @Override
    public void add(int item) {
        if (size >= data.length) {
            throw new ArrayIndexOutOfBoundsException("Impossible to store the element");
        }
        Cell newCell;
        if (head == null) {
            newCell = new Cell(item, null, null);
            head = newCell;
        } else {
            newCell = new Cell(item, head, null);
            head.previous = newCell;
            head = newCell;
        }

        data[size++] = newCell;
    }

    // NULL|1|NULL, NULL|3|1|NULL, NULL|5|3|1|NULL, NULL|7|5|3|1|NULL, NULL|9|7|5|3|1|NULL
    @Override
    public int length() {
        int count = 0;
        Cell crnt = head;

        while (crnt != null) {
            count++;
            crnt = crnt.next;
        }
        return count;
    }

    @Override
    public boolean find(int item) {
        Cell nxt = head;
        for (int i = 0; i < length(); i++) {
            if (nxt.value == item) {
                return true;
            }
            nxt = nxt.next;
        }
        return false;
    }

    @Override
    public void remove(int item) {
        Cell current = head;

        // Search for the node with the given key
        while (current != null) {
            if (current.value == item) {
                // Found the node to remove

                // If the node to remove is the head
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.previous = null;
                    }
                } else {
                    // If the node to remove is not the head
                    current.previous.next = current.next;
                    if (current.next != null) {
                        current.next.previous = current.previous;
                    }
                }

                // Node removed
                return;
            }
            current = current.next;
        }

    }

    public void unlink(Cell cell) {
        if (head == null || cell == null) {
            return;
        }

        if (head == cell) {
            head = head.next;
        }
        if (cell.next != null) {
            cell.next.previous = cell.previous;
        }
        if (cell.previous != null) {
            cell.previous.next = cell.next;
        }
    }

    public void insert(Cell cell) {
        cell.previous = null;
        cell.next = head;
        if (head != null) {
            head.previous = cell;
        }
        head = cell;
    }

    public Cell[] getReferences() {
        return data;
    }

    static class Cell {
        private final int value;
        private Cell next;
        private Cell previous;

        public Cell(int value, Cell next, Cell previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

}