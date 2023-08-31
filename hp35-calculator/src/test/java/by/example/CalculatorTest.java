package by.example;

import by.example.expression.Item;
import by.example.stack.DynamicStack;
import by.example.stack.Stack;
import by.example.stack.StaticStack;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static by.example.Parser.parse;
import static java.lang.System.nanoTime;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'5 3 +', 8",
            "'8 3 -', 5",
            "'8 3 *', 24",
            "'10 5 /', 2",
            "'10 2 5 * +', 20",
            "'10 2 5 + *', 70",
            "'1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 + * + * + * + * + * + * + * +', 20643839"
    })
    void testDynamicStackCalculation(String test, int expectedResult) {
        Item[] expr = parse(test);
        Calculator calc = new Calculator(expr, new DynamicStack());
        assertEquals(expectedResult, calc.run());
    }

    @ParameterizedTest(name = "{0} = {1}")
    @CsvSource({
            "'5 3 +', 8",
            "'8 3 -', 5",
            "'8 3 *', 24",
            "'10 5 /', 2",
            "'10 2 5 * +', 20",
            "'10 2 5 + *', 70",
            "'1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 + * + * + * + * + * + * + * +', 20643839"
    })
    void testStaticStackCalculation(String test, int expectedResult) {
        Item[] expr = parse(test);
        Calculator calc = new Calculator(expr, new StaticStack(expr.length));
        assertEquals(expectedResult, calc.run());
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1_000, 10_000, 100_000})
    void benchmarkStaticStackCalculation(int amountOfNumbers) {
        assertDoesNotThrow(() ->
                execute(new StaticStack(amountOfNumbers * 2), amountOfNumbers)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 1_000, 10_000, 100_000})
    void benchmarkDynamicStackCalculation(int amountOfNumbers) {
        assertDoesNotThrow(() ->
                execute(new DynamicStack(), amountOfNumbers)
        );
    }

    private void execute(Stack stack, int amountOfNumbers) {
        long memBefore = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long time = nanoTime();
        try {
            StringBuilder inputNumbers = new StringBuilder();
            StringBuilder operations = new StringBuilder();
            for (int i = 0; i < amountOfNumbers; i++) {
                inputNumbers
                        .append(i)
                        .append(" ");
                operations
                        .append(" ")
                        .append(
                                i % 31 == 0
                                        ? "*"
                                        : (
                                        (i % 23 == 0)
                                                ? "/"
                                                : (
                                                (i % 19 == 0)
                                                        ? "*"
                                                        : (
                                                        (i % 11 == 0)
                                                                ? "-"
                                                                : "+")))
                        );
            }
            String input = inputNumbers + "123" + operations;
//            System.out.println("input = " + input);
            Item[] expr = parse(input);
            Calculator calc = new Calculator(expr, stack);
            var result = calc.run();
            System.out.println("result = " + result);
        } finally {
            time = nanoTime() - time;
            long memAfter = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
            System.out.printf("%s | Execution time = %dms, Memory used = %dMb%n",
                    stack.getClass(), (time / 1_000_000), (memAfter-memBefore)/1024/1024);
        }
    }


}
