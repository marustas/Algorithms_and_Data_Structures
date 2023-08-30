package by.example;

import by.example.expression.Item;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "'5 3 +', 8",
            "'8 3 -', 5",
            "'8 3 *', 24",
            "'10 5 /', 2",
            "'10 2 5 * +', 20",
            "'10 2 5 + *', 70",
            "'1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 + * + * + * + * + * + * + * +', 20643839"
    })
    public void testCalculator(String test, int expectedResult) {
        Item[] expr = Parser.parse(test);
        Calculator calc = new Calculator(expr);
        assertEquals(expectedResult, calc.run());
    }
}
