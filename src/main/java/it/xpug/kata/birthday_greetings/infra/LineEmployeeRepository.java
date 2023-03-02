package it.xpug.kata.birthday_greetings.infra;

import it.xpug.kata.birthday_greetings.domain.Employee;
import it.xpug.kata.birthday_greetings.domain.EmployeeRepository;
import it.xpug.kata.birthday_greetings.domain.XDate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineEmployeeRepository implements EmployeeRepository {
    private final String fileName;

    public LineEmployeeRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Employee> getEmployeesWhoseBirthdayItIs(XDate xDate) {
        List<Employee> employeeStream = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(this.fileName))) {
            employeeStream = lines
                    .skip(1) // skip header
                    .map((str) -> {
                        String[] employeeData = str.split(", ");
                        return new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                    })
                    .filter(employee -> employee.isBirthday(xDate))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeStream;
    }
}
