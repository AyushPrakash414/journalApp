package net.AyushPrakash.journalApp.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Getter
@Setter
@RestController
@RequestMapping("mail/send")
public class EmailService {
    private String from;
    private String to;
    private String subject;
    private String body;
    @Value("${EMAIL_API_KEY}")
    private String api_key;
    
    @PostMapping
    public ResponseEntity<?> SendEmail(@RequestBody EmailService Smail)
    {
        Email from = new Email(Smail.getFrom());
        String subject = "Sending without SMTP";
        Email to = new Email(Smail.getTo());
        Content content = new Content("text/plain", Smail.getBody());
        Mail mail = new Mail(from, subject, to, content);
        System.out.println(Smail.getApi_key());
        SendGrid sg = new SendGrid(Smail.getApi_key());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println();
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

            return ResponseEntity.ok(response.getStatusCode());
        } catch (IOException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while sending the email: " + ex.getMessage());
        }
    }

}
