package org.example;

public class DoubleLinkedList {
    Cell first;

    public static class Cell {
        int head;
        Cell front;
        Cell behind;

        Cell(int val, Cell frnt, Cell bhnd) {
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
        first.behind = cell;
    }

}