package org.example;

import java.util.Arrays;

public class StringCalculator {
    public static int add(String string) {
        if (!string.isEmpty()) {
            return Arrays.stream(string.split("[,\n]")).mapToInt(Integer::parseInt).sum();
        }
        return 0;
    }
}
