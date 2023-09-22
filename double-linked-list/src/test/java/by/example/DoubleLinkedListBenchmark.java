package by.example;

import java.util.Random;

import by.example.DoubleLinkedList.Cell;

public class DoubleLinkedListBenchmark {

	public static void main(String[] arg) {
		var random = new Random();
		int k = 1000;
		int tries = 1000;
		int[] sizes = { 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000 };
		System.out.print("Number of elements\t\t Time\n");
		for (int size : sizes) {
			double minA = Double.POSITIVE_INFINITY;
			for (int t = 0; t < tries; t++) {
				DoubleLinkedList list = new DoubleLinkedList(size);
				// fill the list with sequential numbers
				for (int i = 0; i < size; i++) {
					list.add(i);
				}
				Cell[] references = list.getReferences();
				double start = System.nanoTime();
				for (int j = 0; j < k; j++) {
					var randomIndex = random.nextInt(size - 1);
					var randomReference = references[randomIndex];
					list.unlink(randomReference);
					list.insert(randomReference);
				}
				double executionTime = System.nanoTime() - start;

				if (executionTime < minA) {
					minA = executionTime;
				}
			}
			System.out.printf("%d\t\t %.2f\n", size, minA);
		}
	}

}
