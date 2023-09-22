package by.example;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class LinkedListTest {

	@Test
	void append() {
		// given
		LinkedList linkedListA = new LinkedList(10);
		LinkedList linkedListB = new LinkedList(5);
		// when
		// then
		assertDoesNotThrow(()-> linkedListA.append(linkedListB));

	}

}