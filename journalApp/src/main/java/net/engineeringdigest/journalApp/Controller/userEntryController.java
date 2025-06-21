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
public class userEntryController {

    @Autowired
    private userEntryService entry;

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody User currentEntry) {
        entry.saveUserEntry(currentEntry);
        return ResponseEntity.ok("User entry created successfully.");
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(entry.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ObjectId id) {
        return entry.getEntryByItsID(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username) {
        return entry.getUserByUserName(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/updateUser/{username}")
    public ResponseEntity<String> updateUserByUserName(@PathVariable String username, @RequestBody User newEntry) {
        return entry.getUserByUserName(username)
                .map(user -> {
                    user.setUserName(newEntry.getUserName());
                    user.setPassword(newEntry.getPassword());
                    entry.updateUser(username,user);
                    return ResponseEntity.ok("Updated");
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable ObjectId id) {
        entry.deleteEntryById(id);
        return ResponseEntity.ok("Deleted");
    }
}
