package com.dodevjutsu.kata.birthdaygreetings;

import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.CannotReadEmployeesException;
import com.dodevjutsu.kata.birthdaygreetings.core.OurDate;
import com.dodevjutsu.kata.birthdaygreetings.infrastructure.repositories.FileEmployeeRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;

public class FileEmployeeRepositoryTest {

    @Rule
    public ExpectedException expected = ExpectedException.none();
    private OurDate ANY_DATE;

    @Before
    public void setUp() throws Exception {
        ANY_DATE = new OurDate("2016/01/01");
    }

    @Test
    public void fails_when_the_file_does_not_exist() throws Exception {

        FileEmployeeRepository service = new FileEmployeeRepository("non-existing.file");
        expected.expect(CannotReadEmployeesException.class);
        expected.expectMessage(containsString("cannot loadFrom file"));
        expected.expectMessage(containsString("non-existing.file"));

        service.whoseBirthdayIs(ANY_DATE);
    }

    @Test
    public void fails_when_the_file_does_not_have_the_necessary_fields() throws Exception {

        FileEmployeeRepository service = new FileEmployeeRepository("src/test/resources/wrong_data__wrong-date-format.csv");
        expected.expect(CannotReadEmployeesException.class);
        expected.expectMessage(containsString("Badly formatted employee birth date in"));

        service.whoseBirthdayIs(ANY_DATE);
    }

}