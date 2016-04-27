package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders;

import javax.mail.MessagingException;

public class CannotSendGreetingsException extends RuntimeException {
    public CannotSendGreetingsException(String cause, MessagingException exception) {
        super(cause, exception);
    }
}
