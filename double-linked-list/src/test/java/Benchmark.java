import org.example.DoubleLinkedList;
import org.example.DoubleLinkedList.Cell;
import java.util.Random;

public class Benchmark {
    public static int[] createData(int n, int bound) {
        Random rnd = new Random();
        int[] array = new int[n];
        var length = array.length;
        for (int a = 0; a < length; a++) {
            array[a] = rnd.nextInt(0, bound - 1);
        }
        return array;
    }

    public static void main(String[] arg) {

        int k = 1000;
        int tries = 1000;
        int[] sizes = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
        System.out.print("Number of elements\t\t Time\n");
        for (int s : sizes) {
            double minA = Double.POSITIVE_INFINITY;
            for (int t = 0; t < tries; t++) {
                DoubleLinkedList list = new DoubleLinkedList(s);
                Cell[] references = list.createCellReferences();
                int[] indices = createData(k, s);

                double start = System.nanoTime();
                for (int index : indices) {
                    list.unlink(references[index]);
                    list.insert(references[index]);
                }
                double executionTime = System.nanoTime() - start;

                if (executionTime < minA) {
                    minA = executionTime;
                }
            }
            System.out.printf("%d\t\t %.2f\n", s, minA );
        }
    }
}
