package by.example;

import by.example.stack.DynamicStack;
import by.example.stack.Stack;
import by.example.stack.StaticStack;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.nanoTime;

public class StackRunner {
    public static void main(String[] args) {
        int amountOfNumbers = 10_000;
        // Memory used:
        // 16 bytes of object overhead
        // 4 bytes for the int length field of the array
        // 4 * length bytes to store the int values (4 bytes per int)
        System.out.println("RESULTS");
        execute(amountOfNumbers, new StaticStack(amountOfNumbers));
        execute(amountOfNumbers, new DynamicStack());
    }

    private static void execute(int amountOfNumbers, Stack stack) {
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long time = nanoTime();
        try {
            var random = ThreadLocalRandom.current();
            for (int i = 0; i < amountOfNumbers; i++) {
                stack.push(random.nextInt(1, amountOfNumbers));
            }
            for (int i = 0; i < amountOfNumbers; i++) {
                stack.pop();
            }

        } finally {
            time = nanoTime() - time;
            long memAfter = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            System.out.printf("%s | %15d elements | Execution time = %5dms | Memory used = %.2fKb%n",
                    stack.getClass(), amountOfNumbers, (time / 1_000_000), ((double) (memAfter - memBefore)) / 1024);
        }
    }
}
