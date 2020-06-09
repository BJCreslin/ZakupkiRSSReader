package ru.bjcreslin.domain.ftp;

import org.springframework.stereotype.Component;
import ru.bjcreslin.configuration.FtpConfiguration;

public class FtpServerImpl implements FtpServer {

    private final String name;
    private final int port;

    public FtpServerImpl() {
        this.name = FtpConfiguration.ADDRESS;
        this.port = 21;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getName() {
        return name;
    }
}
