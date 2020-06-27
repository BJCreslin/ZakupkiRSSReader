package ru.bjcreslin.emailsender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

class EmailServiceImplTest {

    EmailServiceImpl emailService;

    Email email;

    private static class Email {
        String to = "bjcreslin@gmail.com";
        String subj = "tester 12";
        String text = "This is test text";
        String from = "rssreader@inbox.ru";
    }

    @BeforeEach
    void setUp() {
        email = new Email();
        emailService = new EmailServiceImpl(new JavaMailSenderImpl());
    }

    @Test
    void sendSimpleMessageTest() {
        emailService.sendSimpleMessage(email.from, email.to, email.subj, email.text);

    }

}
