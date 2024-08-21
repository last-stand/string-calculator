package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringCalculatorTest {
    @Test
    void shouldReturn_0_ForEmptyString() {
        int result = StringCalculator.add("");

        assertEquals(0, result);
    }

    @Test
    void shouldReturn_1_If_1_PassedAsString() {
        int result = StringCalculator.add("1");

        assertEquals(1, result);
    }

    @Test
    void shouldReturn_3_If_1_And_2_ArePassedAsString() {
        int result = StringCalculator.add("1,2");

        assertEquals(3, result);
    }

    @Test
    void shouldReturn_30_If_5_10_And_15_ArePassedAsString() {
        int result = StringCalculator.add("5,10,15");

        assertEquals(30, result);
    }

    @Test
    void shouldReturn_5_If_3_And_2_ArePassedWithNewLineDelimiter() {
        int result = StringCalculator.add("3\n2");

        assertEquals(5, result);
    }

    @Test
    void shouldReturn_6_If_1_2_And_3_ArePassedWithNewLineAndCommaDelimiters() {
        int result = StringCalculator.add("1\n2,3");

        assertEquals(6, result);
    }
    @Test()
    void shouldThrowErrorIfCommaAndNewLineDelimitersAreTogetherInTheString() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> StringCalculator.add("1,\n"));

        assertTrue(thrown.getMessage().contains("Invalid Input!"));
    }

    @Test()
    void shouldBeAbleToChangeDelimiterToSemicolonToSeparateNumbers() {
        int result = StringCalculator.add("//;\n1;2");

        assertEquals(3, result);
    }

    @Test()
    void shouldThrowErrorIfNegativeNumbersArePassed() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> StringCalculator.add("1,-2,-3"));

        assertTrue(thrown.getMessage().contains("Negatives not allowed: -2,-3"));
    }

    @Test()
    void shouldIgnoreNumbersBiggerThan_1000() {
        int result = StringCalculator.add("12\n1000\n1001");

        assertEquals(1012, result);
    }

    @Test()
    void shouldAllowAsteriskAsCustomDelimiterOfLengthThree() {
        int result = StringCalculator.add("//[***]\n1***2***3");

        assertEquals(6, result);
    }

    @Test()
    void shouldAllowHashAsCustomDelimiterOfLengthFive() {
        int result = StringCalculator.add("//[#####]\n3#####4#####5");

        assertEquals(12, result);
    }

    @Test()
    void shouldAllowMultipleCustomDelimitersLikeAsteriskAndPercentSign() {
        int result = StringCalculator.add("//[*][%]\n1*2%3");

        assertEquals(6, result);
    }

    @Test()
    void shouldAllowMultipleCustomDelimitersLikeHashWithLengthTwoAndDollarWithLengthThree() {
        int result = StringCalculator.add("//[##][$$$]\n50##30$$$28");

        assertEquals(108, result);
    }
}
