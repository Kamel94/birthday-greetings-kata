package it.xpug.kata.birthday_greetings;

public class BirthdayService {

    private final GreetingsSender greetingsSender;
    private final EmployeeRepository employeeRepository;

    public BirthdayService(GreetingsSender greetingsSender, EmployeeRepository employeeRepository) {
        this.greetingsSender = greetingsSender;
        this.employeeRepository = employeeRepository;
    }

    public void sendGreetings(XDate xDate) {
        for (Employee employee : employeeRepository.getEmployeesWhoseBirthdayItIs(xDate)) {
            String recipient = employee.getEmail();
            String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
            String subject = "Happy Birthday!";
            this.greetingsSender.sendMessage("sender@here.com", subject, body, recipient);
        }
    }

}
