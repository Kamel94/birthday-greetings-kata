package it.xpug.kata.birthday_greetings;

public class BirthdayService {

    private final GreetingsSender greetingsSender;
    private final LineEmployeeRepository lineEmployeeRepository;

    public BirthdayService(GreetingsSender greetingsSender, LineEmployeeRepository lineEmployeeRepository) {
        this.greetingsSender = greetingsSender;
        this.lineEmployeeRepository = lineEmployeeRepository;
    }

    public void sendGreetings(String fileName, XDate xDate) {
        for (Employee employee : lineEmployeeRepository.getEmployeesWhoseBirthdayItIs(fileName, xDate)) {
            String recipient = employee.getEmail();
            String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
            String subject = "Happy Birthday!";
            this.greetingsSender.sendMessage("sender@here.com", subject, body, recipient);
        }
    }

}
