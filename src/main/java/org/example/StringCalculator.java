package org.example;

import org.example.exceptions.InvalidInputException;
import org.example.exceptions.NegativesNotAllowedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {
    private static final int MAX_ALLOWED_INTEGER = 1000;

    public static int add(String string) {
        if (string.isEmpty()) {
            return 0;
        }
        List<String> delimiters = List.of(",\n");
        if (string.endsWith(",\n")) {
            throw new InvalidInputException();
        }
        if (string.startsWith("//")) {
            String[] stringParts = splitInput(string);
            delimiters = extractDelimiters(stringParts[0]);
            string = stringParts[1];
        }
        List<Integer> integerList = parseNumbers(string, getDelimiterPattern(delimiters));
        checkNegativeNumbers(integerList);
        return sumValidNumbers(integerList);
    }

    private static String getDelimiterPattern(List<String> delimiters) {
        return "[" + String.join("|", delimiters) + "]";
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

    private static List<String> extractDelimiters(String delimiterInput) {
        List<String> delimiters = extractMultipleDelimiters(delimiterInput);
        if (delimiters.isEmpty()) {
            delimiters.add(String.valueOf(delimiterInput.charAt(2)));
        }
        return delimiters;
    }

    private static List<String> extractMultipleDelimiters(String delimiterInput) {
        String regex = "\\[(.*?)\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(delimiterInput);
        List<String> delimiters = new ArrayList<>();
        while (matcher.find()) {
            String extractedDelimiter = matcher.group();
            delimiters.add(extractedDelimiter);
        }
        return delimiters;
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

    private static List<Integer> parseNumbers(String input, String delimiters) {
        return Arrays.stream(input.split(delimiters))
                .filter(str -> !str.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
