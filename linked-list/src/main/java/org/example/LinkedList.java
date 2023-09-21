package org.example;

public class LinkedList {
    Cell first;
    Cell[] temp;

    public LinkedList(int n) {
        temp = new Cell[n];
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
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
        int head;
        Cell tail;

        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }
    }

    public void add(int item) {
        first = new Cell(item, first);
    }

    public int length() {
        int count = 0;
        Cell crnt = first;

        while (crnt != null) {
            count++;
            crnt = crnt.tail;
        }

        return count;
    }

    public boolean find(int item) {
        Cell nxt = first;
        for (int i = 0; i < length(); i++) {
            if (nxt.head == item) {
                return true;
            } else {
                nxt = nxt.tail;
            }
        }
        return false;
    }

    public void remove(int item) {
        if (first == null) {
            return;
        }

        if (first.head == item) {
            first = first.tail;
            return;
        }

        Cell crnt = first;
        while (crnt.tail != null) {
            if (crnt.tail.head == item) {
                crnt.tail = crnt.tail.tail;
                return;
            }
            crnt = crnt.tail;
        }
    }

    public void append(LinkedList b) {
        Cell nxt = first;
        Cell prv = null;
        while (nxt.tail != null) {
            prv = nxt;
            nxt = nxt.tail;
        }
        nxt.tail = b.first;
        b.first = null;
    }

    public void unlink(Cell cell) {
        if (first == null || cell == null) {
            return;
        }

        if (first == cell) {
            first = first.tail;
            return;
        }

        Cell current = first;
        while (current.tail != null && current.tail != cell) {
            current = current.tail;
        }

        if (current.tail == cell) {
            current.tail = cell.tail;
        }
    }

    public void insert(Cell cell) {
        cell.tail = first;
        first = cell;
    }

}