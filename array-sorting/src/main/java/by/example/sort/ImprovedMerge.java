package by.example.sort;

public class ImprovedMerge implements Sortable{
    @Override
    public void sort(int[] array) {
        if (array.length == 0)
            return;
        int[] aux = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            aux[i] = array[i];
        }
        sort(aux, array, 0, array.length - 1);
    }

    private static void sort(int[] org, int[] aux, int lo, int hi) {
        if (lo != hi) {
            int mid = (lo + hi) / 2;

            sort(aux, org, lo, mid);
            sort(aux, org, mid + 1, hi);

            merge(aux, org, lo, mid, hi);
        }
    }

    private static void merge(int[] org, int[] aux, int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                org[k] = aux[j];
                j++;
            } else if (j > hi) {
                org[k] = aux[i];
                i++;
            } else if (aux[i] < aux[j]) {
                org[k] = aux[i];
                i++;
            } else {
                org[k] = aux[j];
                j++;
            }
        }
    }
}

