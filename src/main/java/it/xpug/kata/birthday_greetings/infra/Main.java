package it.xpug.kata.birthday_greetings.infra;

import it.xpug.kata.birthday_greetings.domain.BirthdayService;
import it.xpug.kata.birthday_greetings.domain.XDate;

public class Main {

    public static void main(String[] args) {
        BirthdayService service = new BirthdayService(new MailSender("localhost", 25), new LineEmployeeRepository("employee_data.txt"));
        service.sendGreetings(new XDate());
    }

}
