package by.example.sort;

public class Insertion implements Sortable {
    @Override
    public void sort(int[] array) {
        var length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j > 0 && (array[j] < array[j - 1]); j--) {
                swap(array, j, j - 1);
            }
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}