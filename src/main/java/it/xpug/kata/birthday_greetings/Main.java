package it.xpug.kata.birthday_greetings;

public class Main {

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(new MailSender("localhost", 25), new LineEmployeeRepository("employee_data.txt"));
        service.sendGreetings(new XDate());
    }

}
