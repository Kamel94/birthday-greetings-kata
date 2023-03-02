package it.xpug.kata.birthday_greetings;

import javax.mail.MessagingException;

public class BirthdayService {

    private final MailSender mailSender;
    private final LineEmployeeRepository lineEmployeeRepository;

    public BirthdayService(MailSender mailSender, LineEmployeeRepository lineEmployeeRepository) {
        this.mailSender = mailSender;
        this.lineEmployeeRepository = lineEmployeeRepository;
    }

    public void sendGreetings(String fileName, XDate xDate) {
        for (Employee employee : lineEmployeeRepository.getEmployeesWhoseBirthdayItIs(fileName, xDate)) {
            String recipient = employee.getEmail();
            String body = "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName());
            String subject = "Happy Birthday!";
            try {
                this.mailSender.sendMessage("sender@here.com", subject, body, recipient);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
