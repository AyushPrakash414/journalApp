package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.userEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class userEntryService {

    @Autowired
    private userEntryRepository repository;

    public void saveUserEntry(User entry) {
        repository.save(entry);
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
