package by.example.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.nanoTime;

class DynamicStackTest {

    @ParameterizedTest
    @ValueSource(ints = {1_000, 10_000, 100_000})
    void benchmark(int amountOfNumbers) {
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long time = nanoTime();
        Stack stack = new DynamicStack();
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
                    stack.getClass(), amountOfNumbers, (time / 1_000_000), ((double)(memAfter - memBefore))/1024);
        }

    }

}