package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

import com.dodevjutsu.kata.birthdaygreetings.core.GreetingMessage;
import com.dodevjutsu.kata.birthdaygreetings.core.GreetingsSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.CannotSendGreetingsException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;

public class EmailGreetingsSender implements GreetingsSender {
    private final EmailSender emailSender;
    private final EmailComposer emailComposer;

    public EmailGreetingsSender(EmailConfiguration config, EmailSender emailSender) {
        this.emailSender = emailSender;
        this.emailComposer = new EmailComposer(config);
    }

    @Override
    public void send(List<GreetingMessage> messages) {
        for (GreetingMessage message : messages) {
            emailSender.sendMessage(
                emailComposer.composeEmailFor(message)
            );
        }
    }

    class EmailComposer {
        private final Session session;
        private final String from;

        public EmailComposer(EmailConfiguration config) {
            this.from = config.from();
            this.session = session(config.smtpHost(), config.smtpPort());
        }

        public Message composeEmailFor(GreetingMessage greetingMessage) {
            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(greetingMessage.to()));
                message.setSubject(greetingMessage.subject());
                message.setText(greetingMessage.text());
                return message;
            } catch (MessagingException exception) {
                throw new CannotSendGreetingsException("failed generating email", exception);
            }
        }

        private Session session(String smtpHost, int smtpPort) {
            java.util.Properties props = new java.util.Properties();
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", String.valueOf(smtpPort));
            return Session.getDefaultInstance(props, null);
        }
    }
}
