package by.example;

import by.example.expression.Item;
import by.example.stack.Stack;

import java.util.Scanner;

public class Calculator {
    Item[] expr;
    int instructionPointer;
    Stack stack;

    public Calculator(Item[] expr) {
        this.expr = expr;
        this.instructionPointer = 0;
        this.stack = new Stack();
    }

    public int run() {
        while (instructionPointer < expr.length) {
            step();
        }
        return stack.pop();
    }

    public void getOperationType(char operator) {
        int y = stack.pop();
        int x = stack.pop();
        int res = 0;
        switch (operator) {
            case '+':
                res = x + y;
                break;
            case '-':
                res = x - y;
                break;
            case '*':
                res = x * y;
                break;
            case '/':
                res = x / y;
                break;
        }
        stack.push(res);
    }

    public void step() {
        Item next = expr[instructionPointer++];
        switch (next.getType()) {
            case ADD: {
                getOperationType('+');
                break;
            }
            case SUB: {
                getOperationType('-');
                break;
            }
            case DIV: {
                getOperationType('/');
                break;
            }
            case MUL: {
                getOperationType('*');
                break;
            }
            case VALUE: {
                stack.push(next.getValue());
                break;
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input;
            while (true) {
                System.out.println("Enter expression or 'exit' to exit:");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                try {
                    Item[] expr = Parser.parse(input);
                    Calculator calc = new Calculator(expr);
                    int res = calc.run();
                    System.out.println("Calculator result: " + res);
                } catch (Exception e) {
                    System.out.printf("An error occurred: %s%n", e.getMessage());
                }
            }
        }
    }
}
