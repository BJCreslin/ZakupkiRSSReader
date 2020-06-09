package ru.bjcreslin.domain.ftp;

import org.springframework.stereotype.Component;

@Component
public class FtpUserImpl implements FtpUser {
    private final String user;
    private final String password;

    public FtpUserImpl() {
        this.user = "free";
        this.password = "free";

    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
