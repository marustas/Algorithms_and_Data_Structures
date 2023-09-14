package by.example.sort;

public class Selection implements Sortable {
	@Override
    public void sort(int[] array) {
		var length = array.length;
		for (int i = 0; i < length - 1; i++) {
			int candidate = i;
			for (int j = i + 1; j < length; j++) {
				if (array[j] < array[candidate]) {
					candidate = j;
				}
			}
			swap(array, i, candidate);
		}
	}

	private void swap(int[] array, int a, int b) {
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
}