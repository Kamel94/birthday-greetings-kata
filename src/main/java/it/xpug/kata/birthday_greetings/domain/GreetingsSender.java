package it.xpug.kata.birthday_greetings.domain;

public interface GreetingsSender {
    void sendMessage(String sender, String subject, String body, String recipient);
}
