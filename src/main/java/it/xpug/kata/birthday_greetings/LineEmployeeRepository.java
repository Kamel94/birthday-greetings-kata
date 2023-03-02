package it.xpug.kata.birthday_greetings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineEmployeeRepository {
    public List<Employee> getEmployeesWhoseBirthdayItIs(String fileName, XDate xDate) {
        List<Employee> employeeStream = new ArrayList<>();
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            employeeStream = lines
                    .skip(1) // skip header
                    .map((str) -> {
                        String[] employeeData = str.split(", ");
                        try {
                            return new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(employee -> employee.isBirthday(xDate))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employeeStream;
    }
}
