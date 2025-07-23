package net.AyushPrakash.journalApp.Controller;

import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.service.UserMailThereJournalEntries;
import net.AyushPrakash.journalApp.service.userEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/SendJournalEntries")
public class EmailThereJournalEntryController
{
    @Autowired
    private UserMailThereJournalEntries userMailThereJournalEntries;
    @Autowired
    private userEntryService service;
    @PostMapping
    public void  sendUserJournalEntries ()
    {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        Optional<User> user=service.getUserByUserName(name);
        userMailThereJournalEntries.sendMail(user.get());
    }
}
