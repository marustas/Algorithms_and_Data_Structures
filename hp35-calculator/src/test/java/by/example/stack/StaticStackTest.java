package by.example.stack;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.ThreadLocalRandom;

import static java.lang.System.nanoTime;

class StaticStackTest {

    @ParameterizedTest
    @ValueSource(ints = {10_000, 100_000, 1_000_000})
    void benchmark(int amountOfNumbers) {
        // Memory used:
        // 16 bytes of object overhead
        // 4 bytes for the int length field of the array
        // 4 * length bytes to store the int values (4 bytes per int)
        long memBefore = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long time = nanoTime();
        Stack stack = new StaticStack(amountOfNumbers);
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