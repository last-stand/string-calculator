package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
