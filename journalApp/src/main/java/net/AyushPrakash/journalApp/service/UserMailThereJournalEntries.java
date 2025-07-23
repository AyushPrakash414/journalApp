package net.AyushPrakash.journalApp.service;

import net.AyushPrakash.journalApp.Controller.EmailController;
import net.AyushPrakash.journalApp.Entity.EmailDTO;
import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.Entity.journalEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMailThereJournalEntries
{
    @Autowired
    private EmailController emailController;

    public ResponseEntity<?> sendMail(User user)
    {
        try {
            List<journalEntry> allEntries=user.getJournalEntries();
            List<String>temp=new ArrayList<>();
            for (journalEntry entry:allEntries)
            {
                temp.add(entry.getContent());
            }
            String allContent=String.join("----->",temp);
            EmailDTO email=new EmailDTO();
            email.setBody(allContent);
            email.setTo("231b081@juetguna.in");
            email.setFrom("prakashayush414@gmail.com");
            email.setSubject(user.getUserName()+" all Journal Entries");
            emailController.sendEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }

}
