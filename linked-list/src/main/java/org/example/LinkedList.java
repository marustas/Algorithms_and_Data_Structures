package org.example;

class LinkedList {
    Cell first;

    private class Cell {
        int head;
        Cell tail;

        Cell(int val, Cell tl) {
            head = val;
            tail = tl;
        }

        private Cell add(int item) {
            return new Cell(item, first);
        }

        private int length() {
            int length = 0;
            Cell nxt = this;
            while (nxt.tail != null) {
                length++;
                nxt = nxt.tail;
            }
            return length;
        }

        private boolean find(int item) {
            Cell nxt = this;
            for (int i = 0; i < length(); i++) {
                if (nxt.head == item) {
                    return true;
                } else {
                    nxt = nxt.tail;
                }
            }
            return false;
        }

        private Cell remove(int item) {
            Cell nxt = this;
            Cell prv = null;
            while (nxt != null) {
                if (nxt.head == item) {
                    prv.tail = nxt.tail;
                    break
                            ;
                }
                prv = nxt;
                nxt = nxt.tail;
            }
            if (this == nxt)
                return null;
            else
                return this;
        }

    }

    public void append(LinkedList b) {
        Cell nxt = this.first;
        Cell prv = null;
        while (nxt.tail != null) {
            prv = nxt;
            nxt = nxt.tail;
        }
        nxt.tail = b.first;
        b.first.tail = null;
    }

}