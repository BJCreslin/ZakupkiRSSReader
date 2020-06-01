package ru.bjcreslin.domain.ftp;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bjcreslin.configuration.FtpConfiguration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@Component
public class FtpClient {
    private final FtpServer ftpServer;

    private final FtpUser ftpUser;

    private final FTPClient ftp;

    @Autowired
    public FtpClient(FtpServer ftpServer, FtpUser ftpUser) {
        this.ftpServer = ftpServer;
        this.ftpUser = ftpUser;
        this.ftp = new FTPClient();
    }

    // constructor

    void open() throws IOException {


        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));

        ftp.connect(ftpServer.getName(), ftpServer.getPort());

        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        ftp.login(ftpUser.getUser(), ftpUser.getPassword());
        ftp.enterLocalPassiveMode();
      //  System.out.println(ftp.printWorkingDirectory());

        String pathName=FtpConfiguration.region + FtpConfiguration.PROCRDURE_ADDRESS+ FtpConfiguration.PREV_MONTH;
        FTPFile[] ftpFile = ftp.listFiles(pathName);
        Arrays.asList(ftpFile).forEach(x -> System.out.println(x.getName()));
    }

    void close() throws IOException {
        ftp.disconnect();
    }
}
