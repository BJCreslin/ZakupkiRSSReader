package ru.bjcreslin.emailsender;

public interface EmailService {
    public void sendSimpleMessage(
            String from, String to, String subject, String text);
}
