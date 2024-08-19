package org.example;

public class InvalidInputException extends RuntimeException {
    private static final String INVALID_INPUT = "Invalid Input!";
    public InvalidInputException() {
        super(INVALID_INPUT);
    }
}
