package org.example;

import java.util.Arrays;

public class StringCalculator {
    public static int add(String string) {
        String delimiter = ",\n";
        if (!string.isEmpty()) {
            String lastTwoChars = string.length() > 1 ? string.substring(string.length() - 2) : "";
            if (lastTwoChars.equals(",\n")) {
                throw new InvalidInputException();
            }
            String fistThreeChars = string.length() > 2 ? string.substring(0, 2) : "";
            if (fistThreeChars.equals("//")) {
                String[] stringArray = string.split("\n");
                String delimiterInput = stringArray[0];
                string = stringArray[1];
                delimiter = String.valueOf(delimiterInput.charAt(2));
            }
            return Arrays.stream(string.split("[" + delimiter + "]")).mapToInt(Integer::parseInt).sum();
        }
        return 0;
    }
}
