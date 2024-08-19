package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCalculatorTest {
    @Test
    void shouldReturnZeroForEmptyString() {
        int result = StringCalculator.add("");

        assertEquals(0, result);
    }
}
