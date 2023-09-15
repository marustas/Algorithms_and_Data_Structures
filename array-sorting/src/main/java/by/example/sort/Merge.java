package by.example.sort;

public class Merge implements Sortable {
	@Override
	public void sort(int[] array) {
		var length = array.length;
		if (length == 0) {
			return;
		}
		int[] aux = new int[length];
		sort(array, aux, 0, length - 1);
	}

	private void sort(int[] org, int[] aux, int lo, int hi) {
		if (hi == lo) {
			return;
		}
		// sort the items from lo to mid
		int mid = (lo + hi) / 2;
		sort(org, aux, lo, mid);
		// sort the items from mid+1 to hi
		sort(org, aux, mid + 1, hi);
		// merge the two sections using the additional array
		merge(org, aux, lo, mid, hi);

	}

	private void merge(int[] org, int[] aux, int lo, int mid, int hi) {
		// copy all items from lo to hi from org to aux
		for (int i = lo; i <= hi; i++) {
			aux[i] = org[i];
		}
		// let's do the merging
		int i = lo; // the index in the first part
		int j = mid + 1; // the index in the second part
		// for all indices from lo to hi
		for (int k = lo; k <= hi; k++) {
			// if i is greater than mid then
			// move the j'th item to the org array, update j
			if (i > mid) {
				org[k] = aux[j];
				j++;
			}
			// else if j is greate than hi then
			// move the i'th item to the org array, update i
			else if (j > hi) {
				org[k] = aux[i];
				i++;
			}
			// else if the i'th item is smaller than the j¨ath item,
			// move the i'th item to the org array, update i
			else if (aux[i] < aux[j]) {
				org[k] = aux[i];
				i++;
			}
			// else
			// move the j'th item to the org array, update j
			else {
				org[k] = aux[j];
				j++;
			}
		}
	}
}
