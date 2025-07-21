package net.AyushPrakash.journalApp.Controller;

import net.AyushPrakash.journalApp.Entity.EmailDTO;
import net.AyushPrakash.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mail/send")
public class EmailController {
    @Autowired
    private EmailDTO email;
    @Autowired
    private EmailService emailService;
    @PostMapping
    public void sendEmail(@RequestBody  EmailDTO email)
    {

        emailService.SendEmail(email);
    }
}
