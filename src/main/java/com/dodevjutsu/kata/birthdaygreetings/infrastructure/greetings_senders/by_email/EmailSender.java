package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.CannotSendGreetingsException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;

public class EmailSender {
    public void sendMessage(Message message) {
        try {
            Transport.send(message);
        } catch (MessagingException exception) {
            throw new CannotSendGreetingsException("failed sending email", exception);
        }
    }
}
