package by.example;

public class T9 {
    private class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[27];
            valid = false;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}