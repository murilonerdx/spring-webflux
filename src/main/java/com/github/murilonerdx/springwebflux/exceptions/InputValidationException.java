package com.github.murilonerdx.springwebflux.exceptions;

public class InputValidationException extends RuntimeException{

    private static final String msg =  "ALLOWED RANGE IS 10 - 20";
    private static final int errorCode = 100;
    private final int input;

    public InputValidationException(int input) {
        super(msg);
        this.input = input;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public int getInput() {
        return input;
    }
}
