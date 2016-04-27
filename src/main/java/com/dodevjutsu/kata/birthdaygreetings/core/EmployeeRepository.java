package com.dodevjutsu.kata.birthdaygreetings.core;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> whoseBirthdayIs(OurDate today);
}
