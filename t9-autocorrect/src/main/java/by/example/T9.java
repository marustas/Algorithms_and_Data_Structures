package by.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class T9 {
    private static class Node {
        public Node[] next;
        public boolean valid;

        public Node() {
            next = new Node[27];
            valid = false;
        }

        private void addRecursive(String word, int index) {
            char c = word.charAt(index);
            int i = code(c);
            Node node = next[i];

            if (node == null) {
                next[i] = new Node();
            }
            if (index == word.length() - 1) {
                next[i].valid = true;
                return;
            }
            index++;
            next[i].addRecursive(word, index);
        }
    }

    Node root;

    private static int index(char key) {
        return (int) key - 1;
    }

    public int key(char character) {
        int j = code(character);
        return j / 3;
    }

    private static char character(int code) {
        return switch (code) {
            case 0 -> 'a';
            case 1 -> 'b';
            case 2 -> 'c';
            case 3 -> 'd';
            case 4 -> 'e';
            case 5 -> 'f';
            case 6 -> 'g';
            case 7 -> 'h';
            case 8 -> 'i';
            case 9 -> 'j';
            case 10 -> 'k';
            case 11 -> 'l';
            case 12 -> 'm';
            case 13 -> 'n';
            case 14 -> 'o';
            case 15 -> 'p';
            case 16 -> 'r';
            case 17 -> 's';
            case 18 -> 't';
            case 19 -> 'u';
            case 20 -> 'v';
            case 21 -> 'x';
            case 22 -> 'y';
            case 23 -> 'z';
            case 24 -> 'å';
            case 25 -> 'ä';
            case 26 -> 'ö';
            default -> ' ';
        };
    }

    private static int code(char w) {
        return switch (w) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            case 'i' -> 8;
            case 'j' -> 9;
            case 'k' -> 10;
            case 'l' -> 11;
            case 'm' -> 12;
            case 'n' -> 13;
            case 'o' -> 14;
            case 'p' -> 15;
            case 'r' -> 16;
            case 's' -> 17;
            case 't' -> 18;
            case 'u' -> 19;
            case 'v' -> 20;
            case 'x' -> 21;
            case 'y' -> 22;
            case 'z' -> 23;
            case 'å' -> 24;
            case 'ä' -> 25;
            case 'ö' -> 26;
            default -> -1;
        };
    }

    private void collect(Node node, ArrayList<String> list, String input, String path) {
        if (input.length() > 0) {
            int index = input.charAt(0) - 48;
            int branch1 = index * 3;
            int branch2 = index * 3 + 1;
            int branch3 = index * 3 + 2;

            Node node1 = node.next[branch1];
            Node node2 = node.next[branch2];
            Node node3 = node.next[branch3];

            if (node1 != null) {
                String key_rest = input.substring(1);
                collect(node1, list, key_rest, path + character(branch1));
            }

            if (node2 != null) {
                String key_rest = input.substring(1);
                collect(node2, list, key_rest, path + character(branch2));
            }

            if (node3 != null) {
                String key_rest = input.substring(1);
                collect(node3, list, key_rest, path + character(branch3));
            }
        } else if (node.valid) {
            list.add(path);
        }
    }

    public void add(String word) {
        root.addRecursive(word, 0);
    }

    public ArrayList<String> decode(String keys) {
        ArrayList<String> list = new ArrayList<String>();
        collect(root, list, keys, "");
        return list;
    }

    T9(String file) {
        root = new Node();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                add(line);
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }

    public static void main(String[] args) {
        String file = "t9-autocorrect/src/main/java/by/example/message.txt";
        T9 t9 = new T9(file);
        System.out.println(t9.decode("165450").toString());
        System.out.println(t9.key('e'));
        System.out.println(t9.key('u'));
        System.out.println(t9.key('r'));
        System.out.println(t9.key('o'));
        System.out.println(t9.key('p'));
        System.out.println(t9.key('a'));
    }
}