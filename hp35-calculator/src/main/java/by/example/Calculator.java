package by.example;

import by.example.expression.Item;
import by.example.stack.DynamicStack;
import by.example.stack.Stack;
import by.example.stack.StaticStack;

import java.util.Scanner;

public class Calculator {
    private final Item[] expr;
    private int instructionPointer;
    private final Stack stack;


    public Calculator(Item[] expr, Stack stack) {
        this.instructionPointer = 0;
        this.expr = expr;
        this.stack = stack;
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
                    System.out.println("Select stack: D - Dynamic, S - Static");
                    String stackType = scanner.nextLine();
                    Stack stack = createStackByType(stackType, expr.length);
                    Calculator calc = new Calculator(expr, stack);
                    int res = calc.run();
                    System.out.println("Calculator result: " + res);
                } catch (Exception e) {
                    System.out.printf("An error occurred: %s%n", e.getMessage());
                }
            }
        }
    }

    private static Stack createStackByType(String stackType, int size) {
        switch (stackType.toUpperCase()) {
            case "D":
                return new DynamicStack();
            case "S":
                return new StaticStack(size);
            default:
                throw new IllegalArgumentException("Invalid stack type");
        }
    }
}
