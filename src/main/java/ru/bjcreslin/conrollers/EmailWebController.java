package ru.bjcreslin.conrollers;

import io.swagger.annotations.Api;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bjcreslin.emailsender.EmailService;
import ru.bjcreslin.emailsender.TestUserSender;

@Controller
@Api
@CrossOrigin
@RequestMapping("/email/")
@Log
public class EmailWebController {
    EmailService emailService;

    @Autowired
    public EmailWebController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("")
    public void sendTestEmail() {
        var user = new TestUserSender();
        log.info("email");
        emailService.sendSimpleMessage(user.getFrom(),user.getTo(), user.getSubj(), user.getText());
    }

}
