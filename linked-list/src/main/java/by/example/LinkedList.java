package by.example;

public class LinkedList implements List {
    private int size = 0;
    private Cell head;
    private final Cell[] data;

    public LinkedList(int n) {
        data = new Cell[n];
    }

    public void unlink(Cell cell) {
        if (head == null || cell == null) {
            return;
        }
        if (head == cell) {
            head = head.next;
        } else {
            Cell current = head;
            while (current.next != cell) {
                current = current.next;
            }
            current.next = cell.next;
        }

    }

    public void insert(Cell cell) {
        cell.next = head;
        head = cell;
    }

    public Cell[] getReferences() {
        return data;
    }

    @Override
    public void add(int item) {
        if (size >= data.length) {
            throw new ArrayIndexOutOfBoundsException("Impossible to store the element");
        }
        Cell newCell;
        if (head == null) {
            newCell = new Cell(item, null);
            head = newCell;
        } else {
            newCell = new Cell(item, head);
            head = newCell;
        }

        data[size++] = newCell;
    }

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
            if (nxt.head == item) {
                return true;
            } else {
                nxt = nxt.next;
            }
        }
        return false;
    }

    public boolean findCell(Cell cell) {
        Cell nxt = head;
        while (nxt.next != cell) {
            nxt = nxt.next;
            if (nxt.next == cell) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove(int item) {
        if (head == null) {
            return;
        }

        if (head.head == item) {
            head = head.next;
            return;
        }

        Cell crnt = head;
        while (crnt.next != null) {
            if (crnt.next.head == item) {
                crnt.next = crnt.next.next;
                return;
            }
            crnt = crnt.next;
        }
    }

    public void append(LinkedList anotherList) {
        Cell nxt = head;
        while (nxt.next != null) {
            nxt = nxt.next;
        }
        nxt.next = anotherList.head;
        anotherList.head = null;
    }


    @Override
    public String toString() {
        return "LinkedList{" +
                "length=" + length()
                + "}";
    }

    static class Cell {
        int head;
        Cell next;

        Cell(int element, Cell tl) {
            head = element;
            next = tl;
        }
    }
}