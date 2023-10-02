import by.example.QueueBinaryTree;

public class Benchmark {
    public static void main(String[] args) {
        QueueBinaryTree tree = new QueueBinaryTree();
        tree.add(5, 105);
        tree.add(2, 102);
        tree.add(7, 107);
        tree.add(1, 101);
        tree.add(8, 108);
        tree.add(6, 106);
        tree.add(3, 103);
        for (int i : tree)
            System.out.println("next value " + i);
    }
}
