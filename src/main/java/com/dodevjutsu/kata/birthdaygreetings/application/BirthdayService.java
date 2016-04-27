package com.dodevjutsu.kata.birthdaygreetings.application;

import com.dodevjutsu.kata.birthdaygreetings.core.*;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailConfiguration;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailGreetingsSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.greetings_senders.by_email.EmailSender;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.FileEmployeeRepository;

import java.util.List;

import static java.util.stream.Collectors.*;

public class BirthdayService {

    private EmployeeRepository employeeRepository;
    private GreetingsSender greetingsSender;

    public BirthdayService(EmployeeRepository employeeRepository, GreetingsSender greetingsSender) {
        this.employeeRepository = employeeRepository;
        this.greetingsSender = greetingsSender;
    }

    public void sendGreetings(OurDate today) {
        send(greetingMessagesFor(employeesHavingBirthday(today)));
    }

    private List<GreetingMessage> greetingMessagesFor(List<Employee> employees) {
        return employees.stream().map(GreetingMessage::generateFor).collect(toList());
    }

    private void send(List<GreetingMessage> greetingMessages) {
        greetingsSender.send(greetingMessages);
    }

    private List<Employee> employeesHavingBirthday(OurDate today) {
        return employeeRepository.whoseBirthdayIs(today);
    }

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
            new FileEmployeeRepository("employee_data.txt"),
            new EmailGreetingsSender(new EmailConfiguration("sender@here.com", "localhost", 25), new EmailSender())
        );
        try {
            service.sendGreetings(new OurDate("2008/10/08"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
