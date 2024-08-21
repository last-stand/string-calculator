package org.example;

import org.example.exceptions.InvalidInputException;
import org.example.exceptions.NegativesNotAllowedException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
    private static final int MAX_ALLOWED_INTEGER = 1000;

    public static int add(String string) {
        if (string.isEmpty()) {
            return 0;
        }
        String delimiter = ",\n";
        if (string.endsWith(",\n")) {
            throw new InvalidInputException();
        }
        if (string.startsWith("//")) {
            String[] stringParts = splitInput(string);
            delimiter = extractDelimiter(stringParts[0]);
            string = stringParts[1];
        }
        List<Integer> integerList = parseNumbers(string, "[" + delimiter + "]");
        checkNegativeNumbers(integerList);
        return sumValidNumbers(integerList);
    }

    private static int sumValidNumbers(List<Integer> integerList) {
        return integerList.stream().mapToInt(Integer::intValue).filter(num -> num <= MAX_ALLOWED_INTEGER).sum();
    }

    private static String[] splitInput(String input) {
        String[] stringParts = input.split("\n", 2);
        if (stringParts.length != 2 || stringParts[1].isEmpty()) {
            throw new InvalidInputException();
        }
        return stringParts;
    }

    private static String extractDelimiter(String delimiterInput) {
        return String.valueOf(delimiterInput.charAt(2));
    }

    private static void checkNegativeNumbers(List<Integer> numbers) {
        String negativeNumbersString = numbers.stream()
                .filter((num) -> num < 0)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        if (!negativeNumbersString.isEmpty()) {
            throw new NegativesNotAllowedException(negativeNumbersString);
        }
    }

    private static List<Integer> parseNumbers(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
