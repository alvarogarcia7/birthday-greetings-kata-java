package com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories;

import com.dodevjutsu.kata.birthdaygreetings.core.Employee;
import com.dodevjutsu.kata.birthdaygreetings.core.EmployeeRepository;
import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;

import java.util.List;
import java.util.stream.Collectors;

public class FileEmployeeRepository implements EmployeeRepository {
    private final String path;

    public FileEmployeeRepository(String path) {
        this.path = path;
    }

    @Override
    public List<Employee> whoseBirthdayIs(OurDate today) {
        return allEmployees().stream()
            .filter(employee -> employee.isBirthday(today))
            .collect(Collectors.toList());
    }

    private List<Employee> allEmployees() {
        EmployeesFile employeesFile = EmployeesFile.loadFrom(path);
        return employeesFile.extractEmployees();
    }
}
