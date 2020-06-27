package ru.bjcreslin.emailsender;

import lombok.Data;

@Data
public class TestUserSender {
    private final String to="bjcreslin@gmail.com";
    private final String subj="tester 12";
    private final String text="This is test text";
    private final String from="rssreader@inbox.ru";
}
