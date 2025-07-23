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
import net.AyushPrakash.journalApp.Entity.EmailDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;
@Component
@Getter
@Setter
public class EmailService {
    @Value("${EMAIL_API_KEY}")
    private String api_key;

    public ResponseEntity<?> SendEmail(@NotNull EmailDTO Smail)
    {
        Email from = new Email(Smail.getFrom());
        String subject = Smail.getSubject();
        Email to = new Email(Smail.getTo());
        Content content = new Content("text/plain", Smail.getBody());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(getApi_key());
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
