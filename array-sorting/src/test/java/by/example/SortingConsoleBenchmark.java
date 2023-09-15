package by.example;

import static by.example.TestDataUtils.createData;

import by.example.sort.Insertion;
import by.example.sort.Merge;
import by.example.sort.Selection;
import by.example.sort.Sortable;

public class SortingConsoleBenchmark {

	public static void main(String[] arg) {
		int[] sizes = { 1_000, 10_000 };
		int loop = 10;
		int tries = 100;
		System.out.printf("Number of elements\t Selection time\t Insertion time\t Merge time\t Ratio%n");
		for (int s : sizes) {

			int[] array = createData(s);

			var divisor = loop * 1000;
			double ratio = (getMinExecutionTime(new Insertion(), tries, loop, array) / divisor) / (getMinExecutionTime(new Selection(), tries, loop, array) / divisor);
			System.out.printf("%d\t\t\t\t%8.0f\t\t\t%8.0f\t\t%8.0f\t%.2f\n",
					s,
					getMinExecutionTime(new Selection(), tries, loop, array) / divisor,
					getMinExecutionTime(new Insertion(), tries, loop, array) / divisor,
					getMinExecutionTime(new Merge(), tries, loop, array) / divisor,
					ratio);

		}
	}

	private static double getMinExecutionTime(Sortable algorithm, int tries, int loop, int[] array) {
		double min = Double.POSITIVE_INFINITY;
		for (int i = 0; i < tries; i++) {
			double selectionStart = System.nanoTime();
			for (int j = 0; j < loop; j++) {
				int[] clone = array.clone();
				algorithm.sort(clone);
			}
			double executionTime = System.nanoTime() - selectionStart;
			if (executionTime < min) {
				min = executionTime;
			}
		}
		return min;
	}
}
