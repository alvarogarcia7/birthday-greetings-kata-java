package com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email;

public class EmailConfiguration {
    private int smtpPort;
    private String smtpHost;
    private String from;

    public EmailConfiguration(String from, String smtpHost, int smtpPort) {
        this.from = from;
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    public int smtpPort() {
        return smtpPort;
    }

    public String smtpHost() {
        return smtpHost;
    }

    public String from() {
        return from;
    }
}
