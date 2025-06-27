package net.AyushPrakash.journalApp.Controller;

import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.Entity.journalEntry;
import net.AyushPrakash.journalApp.service.journalEntryService;
import net.AyushPrakash.journalApp.service.userEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class journalController {

    @Autowired
    private journalEntryService entry;
    @Autowired
    private userEntryService userService;

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody journalEntry newjournalEntry) {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        try {
            String name= auth.getName();
            entry.saveJournalEntry(newjournalEntry,name);
            return new ResponseEntity<>("Created",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getjournalEntryOfUser() {
        Authentication auth =SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        Optional<User> user=userService.getUserByUserName(name);
        List<journalEntry>allEntry=user.get().getJournalEntries();
        if (!allEntry.isEmpty())
        {
            return new ResponseEntity<>(allEntry,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("No Journal Entries!!",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryByid(@PathVariable ObjectId myid) {
        Authentication Auth=SecurityContextHolder.getContext().getAuthentication();
        String username=Auth.getName();
        Optional<journalEntry> returnEntry=entry.getEntryByItsID(myid);
        if (returnEntry.isPresent())
        {
            return new ResponseEntity<>(returnEntry,HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")

    public ResponseEntity<?> DeleteByID(@PathVariable ObjectId id)
    {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        String name= auth.getName();
        try {
            entry.DeleteByID(name,id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId myId, @RequestBody journalEntry newEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        Optional<User> user = userService.getUserByUserName(userName);
        List<journalEntry> collect = user.get().getJournalEntries().stream()
                .filter(x -> {
                    try {
                        return new ObjectId(x.getId()).equals(myId);
                    } catch (IllegalArgumentException e) {
                        return false; // skip invalid ObjectId strings
                    }
                })
                .collect(Collectors.toList());
        if (!collect.isEmpty()) {
            Optional<journalEntry> journalEntry = entry.getEntryByItsID(myId);
            if (journalEntry.isPresent()) {
                journalEntry old = journalEntry.get();
                old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                entry.updateTheEntryById(old);
                return new ResponseEntity<>(old, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
