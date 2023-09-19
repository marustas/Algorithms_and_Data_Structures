package org.example;

public class LinkedList {
    Cell first;

    public LinkedList(int n) {
        Cell last = null;
        for (int i = 0; i < n; i++) {
            last = new Cell(i, last);
        }
        first = last;
    }

    private static class Cell {
        int head;
        Cell tail;

        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }
    }

    public Cell add(int item) {

        return new Cell(item, first);
    }

    public int length() {
        int length = 1;
        if (first.tail != null) {
            Cell nxt = first;
            while (nxt.tail != null) {
                length++;
                nxt = nxt.tail;
            }
        } else {
            length = 0;
        }
        return length;
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

    public Cell remove(int item) {
        Cell nxt = first;
        Cell prv = null;
        while (nxt != null) {
            if (nxt.head == item) {
                prv.tail = nxt.tail;
                break;
            }
            prv = nxt;
            nxt = nxt.tail;
        }
        if (first == nxt)
            return null;
        else
            return first;
    }

    public void append(LinkedList b) {
        Cell nxt = first;
        while (nxt.tail != null) {
            nxt = nxt.tail;
        }
        nxt.tail = b.first;
        b.first = null;
    }

}