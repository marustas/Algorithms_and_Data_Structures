package by.example;

import java.util.Random;

public final class TestDataUtils {
	private TestDataUtils() {

	}

	public static int[] createData(int n) {
		Random rnd = new Random();
		int[] array = new int[n];
		var length = array.length;
		for (int a = 0; a < length; a++) {
			array[a] = rnd.nextInt(n * 10);
		}
		return array;
	}
}
