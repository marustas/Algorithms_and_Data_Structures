package by.example;

public class BinarySearchTree {
	public static void main(String[] args) {
		BinaryTree<Integer, String> bst = new BinaryTree<>();

		bst.add(5, "Value 5");
		bst.add(3, "Value 3");
		bst.add(7, "Value 7");
		bst.add(2, "Value 2");
		bst.add(4, "Value 4");
		bst.add(6, "Value 6");
		bst.add(8, "Value 8");

		String value = bst.lookup(4);
		System.out.println(value);  // Output: Value 4

		value = bst.lookup(10);
		System.out.println(value);  // Output: null

		System.out.println("#################");

		BinaryTree<Integer, Integer> tree = new BinaryTree<>();
		tree.add(5, 105);
		tree.add(2, 102);
		tree.add(7, 107);
		tree.add(1, 101);
		tree.add(8, 108);
		tree.add(6, 106);
		tree.add(3, 103);
		for (int i : tree) {
			System.out.println("next value " + i);
		}
	}

}

