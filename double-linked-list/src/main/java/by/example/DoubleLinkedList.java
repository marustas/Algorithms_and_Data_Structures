package by.example;

public class DoubleLinkedList {
    Cell first;
    Cell[] temp;

    public DoubleLinkedList(int n) {
        temp = new Cell[n];
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last, null);
            temp[i] = last;
        }
        first = last;
    }

    public Cell[] createCellReferences() {
        Cell[] array = new Cell[temp.length];
        for (int i = 0; i < temp.length; i++) {
            array[i] = temp[i];
        }
        return array;
    }

    public static class Cell {
        public int head;
        Cell front;
        Cell behind;

        public Cell(int val, Cell frnt, Cell bhnd) {
            head = val;
            front = frnt;
            behind = bhnd;
        }
    }

    public void add(int item) {
        first = new Cell(item, first, null);
    }

    public int length() {
        int count = 0;
        Cell crnt = first;

        while (crnt != null) {
            count++;
            crnt = crnt.front;
        }
        return count;
    }

    public boolean find(int item) {
        Cell nxt = first;
        for (int i = 0; i < length(); i++) {
            if (nxt.head == item) {
                return true;
            } else {
                nxt = nxt.front;
            }
        }
        return false;
    }

    public void remove(int item) {
        Cell current = first;

        // Search for the node with the given key
        while (current != null) {
            if (current.head == item) {
                // Found the node to remove

                // If the node to remove is the head
                if (current == first) {
                    first = current.front;
                    if (first != null) {
                        first.behind = null;
                    }
                } else {
                    // If the node to remove is not the head
                    current.behind.front = current.front;
                    if (current.front != null) {
                        current.front.behind = current.behind;
                    }
                }

                // Node removed
                return;
            }
            current = current.front;
        }

    }

    public void unlink(Cell cell) {
        if (first == null || cell == null) {
            return;
        }

        if (first == cell) {
            first = first.front;
        }
        if (cell.front != null) {
            cell.front.behind = cell.behind;
        }
        if (cell.behind != null) {
            cell.behind.front = cell.front;
        }
    }

    public void insert(Cell cell) {
        cell.behind = null;
        cell.front = first;
        if (first != null) {
            first.behind = cell;
        }
        first = cell;
    }

}