package org.example;

import java.util.Arrays;

public class StringCalculator {
    public static int add(String string) {
        if (!string.isEmpty()) {
            String lastTwoChars = string.length() > 1 ? string.substring(string.length() - 2) : "";
            if (lastTwoChars.equals(",\n")) {
                throw new InvalidInputException();
            }
            return Arrays.stream(string.split("[,\n]")).mapToInt(Integer::parseInt).sum();
        }
        return 0;
    }
}
