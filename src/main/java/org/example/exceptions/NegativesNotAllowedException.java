package org.example.exceptions;

public class NegativesNotAllowedException extends RuntimeException{
    private static final String NEGATIVES_NOT_ALLOWED = "Negatives not allowed: ";
    public NegativesNotAllowedException(String negativeNumbers) {
        super(NEGATIVES_NOT_ALLOWED + negativeNumbers);
    }
}
