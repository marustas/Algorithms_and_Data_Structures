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

    private static int code(char w) {
        switch (w) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            case 'k':
                return 10;
            case 'l':
                return 11;
            case 'm':
                return 12;
            case 'n':
                return 13;
            case 'o':
                return 14;
            case 'p':
                return 15;
            case 'r':
                return 16;
            case 's':
                return 17;
            case 't':
                return 18;
            case 'u':
                return 19;
            case 'v':
                return 20;
            case 'x':
                return 21;
            case 'y':
                return 22;
            case 'z':
                return 23;
            case 'å':
                return 24;
            case 'ä':
                return 25;
            case 'ö':
                return 26;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}