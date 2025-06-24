package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.Repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {
    @Autowired
    private userEntryService userService;

    @Autowired
    private journalEntryRepository repository;
    @Transactional
    public void saveJournalEntry(journalEntry entry,String UserName)
    {
        Optional<User> user = userService.getUserByUserName(UserName);
        if (user.isPresent()) {
            journalEntry saved = repository.save(entry);
            user.get().getJournalEntries().add(saved);
            userService.SaveUser(user.get());
        } else {
            throw new RuntimeException("User not found: " + UserName);
        }

    }

    public List<journalEntry> GetAll()
    {
        return repository.findAll();
    }

    public Optional<journalEntry> getEntryByItsID(ObjectId id)
    {
        return repository.findById(id);
    }

    public void updateTheEntryById(journalEntry newEntry)
    {
        repository.save(newEntry);
    }

    public void DeleteByID(String UserName,ObjectId id)
    {
        Optional<User> user = userService.getUserByUserName(UserName);
        user.get().getJournalEntries().removeIf(x -> x.getId() != null && x.getId().equals(id));
            repository.deleteById(id);

    }

}
