package net.AyushPrakash.journalApp.scheduler;

import net.AyushPrakash.journalApp.Controller.AdminController;
import net.AyushPrakash.journalApp.Entity.EmailDTO;
import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.Entity.journalEntry;
import net.AyushPrakash.journalApp.Repository.UserRepositoryIMPL;
import net.AyushPrakash.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class FechUserSendmail {

    @Autowired
    private AdminController adminController;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepositoryIMPL userRepositoryIMPL;

    public void  ferchUserSendMail()
    {
        ResponseEntity<?> response = adminController.GetAll();
        List<User> users = (List<User>) response.getBody();  // ✅ Extract the actual list

        List<String>storeContent=new ArrayList<>();
        for (User ListUser:users)
        {
            List<journalEntry>entries=ListUser.getJournalEntries();
            for (journalEntry ListEntry:entries)
            {
                storeContent.add(ListEntry.getContent());
            }

        }
        String x= String.join(",", storeContent);
        EmailDTO email=new EmailDTO();
        email.setBody(x);
        email.setTo("231b081@juetguna.in");
        email.setFrom("prakashayush414@gmail.com");
        email.setSubject("testing mail");
        emailService.SendEmail(email);
    }


}
