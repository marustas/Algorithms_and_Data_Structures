import by.example.BinarySearchTree;

public class Benchmark {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(5,105);
        tree.add(2,102);
        tree.add(7,107);
        tree.add(1,101);
        tree.add(8,108);
        tree.add(6,106);
        tree.add(3,103);
        for (int i : tree)
            System.out.println("next value " + i);
//        var random = new Random();
//        int k = 1_000;
//        int tries = 100_000;
//        int bound = 100_000;
//        int[] sizes = {10, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
//        System.out.print("Number of nodes\tTime\n");
//        for (int size : sizes) {
//            double min = Double.POSITIVE_INFINITY;
//            for (int t = 0; t < tries; t++) {
//                int[] randomKeys = new int[k];
//                BinaryTree tree = new BinaryTree();
//
//                for (int i = 0; i < k; i++) {
//                    randomKeys[i] = random.nextInt(bound);
//                }
//
//                for (int j = 0; j < size; j++) {
//                    tree.add(random.nextInt(bound), random.nextInt(bound));
//                }
//
//                double start = System.nanoTime();
//                for (int randomKey : randomKeys) {
//                    tree.lookup(randomKey);
//                }
//                double executionTime = System.nanoTime() - start;
//
//                if (executionTime < min) {
//                    min = executionTime;
//                }
//            }
//            System.out.printf("%d\t\t\t\t%.2f\n", size, min / 1000);
//        }
    }
}
