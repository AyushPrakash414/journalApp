package net.engineeringdigest.journalApp.Controller;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.service.userEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userEntryController
{

    @Autowired
    private userEntryService entry;

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody User currentEntry) {
        entry.saveUserEntry(currentEntry);
        return ResponseEntity.ok("user entry created successfully.");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAlluser() {
        List<User> allEntries = entry.GetAll();
        return ResponseEntity.ok(allEntries);
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getuserByid(@PathVariable ObjectId myid) {
        Optional<User> result = entry.getEntryByItsID(myid);
        return ResponseEntity.ok(result);
    }


}
