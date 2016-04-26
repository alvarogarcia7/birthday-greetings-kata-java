package com.dodevjutsu.kata.birthdaygreetings;

import java.util.List;

import static java.util.stream.Collectors.*;

public class BirthdayService {

    private EmployeeRepository employeeRepository;
    private GreetingsService greetingsService;

    public BirthdayService(EmployeeRepository employeeRepository, GreetingsService greetingsService) {
        this.employeeRepository = employeeRepository;
        this.greetingsService = greetingsService;
    }

    public void sendGreetings(OurDate ourDate) {
        List<Employee> employees = getEmployeesHavingBirthdayOn(ourDate);
        send(greetingMessages(employees));
    }

    private List<GreetingMessage> greetingMessages(List<Employee> employees) {
        return employees.stream().map(GreetingMessage::generateFor).collect(toList());
    }

    private void send(List<GreetingMessage> birthdayEmployees) {
        greetingsService.sendGreetingsTo(birthdayEmployees);
    }

    private List<Employee> getEmployeesHavingBirthdayOn(OurDate ourDate) {
        return employeeRepository.whoseBirthdayIsOn(ourDate);
    }

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(
                new FileEmployeeRepository("employee_data.txt"),
                new EmailGreetingsService("localhost", 25));
        try {
            service.sendGreetings(new OurDate("2008/10/08"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
