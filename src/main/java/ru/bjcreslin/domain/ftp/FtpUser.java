package ru.bjcreslin.domain.ftp;

import org.springframework.stereotype.Component;

@Component
public interface FtpUser {
    String getUser();

    String getPassword();
}
