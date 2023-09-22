package by.example;

public class LinkedList {
	public LinkedList(int n) {
		Cell last = null;
		for (int i = 0; i < n; i++) {
			last = new Cell(i, last);
		}
		first = last;
	}

	Cell first;

	static class Cell {
		int head;
		Cell tail;

		Cell(int element, Cell tl) {
			head = element;
			tail = tl;
		}
	}

	public void add(int element) {
		first = new Cell(element, first);
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

	public void append(LinkedList anotherList) {
		Cell nxt = first;
		while (nxt.tail != null) {
			nxt = nxt.tail;
		}
		nxt.tail = anotherList.first;
		anotherList.first = null;
	}


	@Override
	public String toString() {
		return "LinkedList{" +
				"length=" + length()
				+ "}";
	}
}