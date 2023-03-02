package it.xpug.kata.birthday_greetings.infra;

import it.xpug.kata.birthday_greetings.domain.GreetingsSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender implements GreetingsSender {

    private final String smtpHost;
    private final int smtpPort;

    public MailSender(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void sendMessage(String sender, String subject, String body, String recipient) {
        Session session = createMailSession();

        try {
            Message msg = constructMessage(sender, subject, body, recipient, session);
            Transport.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message constructMessage(String sender, String subject, String body, String recipient, Session session) throws MessagingException {
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setText(body);
        return msg;
    }

    private Session createMailSession() {
        java.util.Properties props = new java.util.Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getInstance(props, null);
    }
}
