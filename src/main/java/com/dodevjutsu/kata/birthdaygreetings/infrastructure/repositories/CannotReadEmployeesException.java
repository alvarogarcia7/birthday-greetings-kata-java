package com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories;

public class CannotReadEmployeesException extends RuntimeException {
    public CannotReadEmployeesException(String cause, Exception exception) {
        super(cause, exception);
    }
}
