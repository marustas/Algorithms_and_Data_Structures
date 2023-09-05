public class BinarySearch {
    public static boolean binary_search(int[] array, int key) {
        int first = 0;
        int last = array.length - 1;
        while (true) {
            int index = array.length / 2;
            if (array[index] == key) {
                return true;
            }
            if (array[index] < key && index < last) {
                first = index + 1;
                continue;
            }
            if (array[index] > key && index > first) {
                last = index - 1;
                continue;
            }
// Why do we land here? What should we do?
            if (first > last) {
                return false;
            }
        }
    }
}
