package org.example;

import org.example.exceptions.InvalidInputException;
import org.example.exceptions.NegativesNotAllowedException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    private static final int MAX_ALLOWED_INTEGER = 1000;

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
            List<Integer> integerList = Arrays.stream(string.split("[" + delimiter + "]"))
                    .mapToInt(Integer::parseInt).boxed().toList();
            String negativeNumbersString = integerList.stream()
                    .filter((num) -> num < 0)
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            if (!negativeNumbersString.isEmpty()) {
                throw new NegativesNotAllowedException(negativeNumbersString);
            }
            return integerList.stream().mapToInt(Integer::intValue).filter(num -> num <= MAX_ALLOWED_INTEGER).sum();
        }
        return 0;
    }
}
