package net.AyushPrakash.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.Repository.userEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Slf4j
@Component
public class userEntryService {
    //private static final Logger logger = LoggerFactory.getLogger(userEntryService.class);

    @Autowired
    private userEntryRepository repository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public void saveNewUser(User entry) {
        try {
            entry.setPassword(passwordEncoder.encode(entry.getPassword()));
            entry.setRoles(List.of("user"));
            repository.save(entry);
        }
        catch(Exception e)
        {
            log.info("haaaaaaaaaaaaaaaaaa! {}:",e.getMessage());

        }
    }

    public void SaveUser(User user)
    {
        repository.save(user);
    }

    public boolean updateUser(String username, User newEntry) {
        Optional<User> userOpt = repository.findByUserName(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUserName(newEntry.getUserName());
            user.setPassword(passwordEncoder.encode(newEntry.getPassword()));
            repository.save(user);
            return true;
        }
        return false;
    }
    public List<User> getAll() {
        return repository.findAll();
    }

    public Optional<User> getEntryByItsID(ObjectId id) {
        return repository.findById(id);
    }

    public Optional<User> getUserByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    public void deleteEntryById(ObjectId id) {
        repository.deleteById(id);
    }
}
