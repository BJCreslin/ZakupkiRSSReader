package ru.bjcreslin.domain.ftp;

import org.springframework.stereotype.Component;

public interface FtpServer {
    int getPort();

    String getName();
}
