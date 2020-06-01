package ru.bjcreslin.domain.ftp;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class FtpClientTest {

    @Test
    void open() {
        FtpClient ftpClient = new FtpClient(new FtpServerImpl(), new FtpUserImpl());
        try {
            ftpClient.open();
            ftpClient.close();
        } catch (IOException e) {
            System.out.println("Do not connect");
        }
    }

    @Test
    void close() {
    }
}
