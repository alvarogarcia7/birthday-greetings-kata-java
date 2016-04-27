package com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories;

import com.dodevjutsu.kata.birthdaygreetings.core.Employee;
import com.dodevjutsu.kata.birthdaygreetings.core.EmployeeRepository;
import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;

import java.util.List;
import java.util.stream.Collectors;

public class FileEmployeeRepository implements EmployeeRepository {
    private final String fileName;

    public FileEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> whoseBirthdayIs(OurDate today) {
        return allEmployees().stream()
            .filter(employee -> employee.isBirthday(today))
            .collect(Collectors.toList());
    }

    private List<Employee> allEmployees() {
        Lines lines = FileReader.readFileLinesAfterHeader(fileName);
        return lines.extractEmployees();
    }
}
