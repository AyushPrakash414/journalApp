package net.AyushPrakash.journalApp.Controller;

import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.service.userEntryService;
import net.AyushPrakash.journalApp.Repository.userEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class userEntryController {
    @Autowired
    private userEntryRepository repo;

    @Autowired
    private userEntryService entry;

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

    @PutMapping
    public ResponseEntity<String> updateUserByUserName( @RequestBody User UpdatedEntry) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String name=authentication.getName();
        Optional<User>UserInDB=entry.getUserByUserName(name);
        if (UserInDB.isPresent())
        {

            UserInDB.get().setUserName(UpdatedEntry.getUserName());
            UserInDB.get().setPassword(UpdatedEntry.getPassword());
            entry.saveNewUser(UserInDB.get());
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserById() {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        repo.deleteByUserName(authentication.getName());
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
}
