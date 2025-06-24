package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.service.userEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publicController")
public class publicController {
    @Autowired
    private userEntryService entry;
    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody User currentEntry) {
        entry.saveNewUser(currentEntry);
        return ResponseEntity.ok("User entry created successfully.");
    }

}
