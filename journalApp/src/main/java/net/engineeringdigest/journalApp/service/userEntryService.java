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

    public void saveUserEntry(User entry)
    {
        repository.save(entry);
    }

    public List<User> GetAll()
    {
        return repository.findAll();
    }

    public Optional<User> getEntryByItsID(ObjectId id)
    {
        return repository.findById(id);
    }

    public void updateTheEntryById(ObjectId id, User newEntry)
    {
        repository.save(newEntry);
    }

    public String DeleteTheEntryById(ObjectId id)
    {
        repository.deleteById(id);
        return "Deleted!!";
    }

}
