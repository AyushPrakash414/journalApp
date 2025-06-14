package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.service.journalEntryService;
import net.engineeringdigest.journalApp.service.userEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalController {

    @Autowired
    private journalEntryService entry;
    @Autowired
    private userEntryService userService;

    @PostMapping ("{userName}")
    public ResponseEntity<String> createEntry(@RequestBody journalEntry currentEntry ,@PathVariable String userName) {
        entry.saveJournalEntry(currentEntry,userName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("{userName}")
    public ResponseEntity<?> getAllJournalEntry(@PathVariable String userName) {
        Optional<User> user=userService.getUserByUserName(userName);
        List<journalEntry> allEntries = user.get().getJournalEntries();
        if (!allEntries.isEmpty())
        {
            return new ResponseEntity<>(allEntries,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryByid(@PathVariable ObjectId myid) {
        Optional<User> user=userService.getEntryByItsID(myid);
        if (user.isPresent())
        {
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("id/{id}/{UserKaNaam}")

    public ResponseEntity<?> DeleteByID(@PathVariable String UserKaNaam,@PathVariable ObjectId id)
    {
        entry.DeleteByID(UserKaNaam,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
