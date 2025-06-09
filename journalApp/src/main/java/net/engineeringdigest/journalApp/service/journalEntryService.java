package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.Repository.journalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {

    @Autowired
    private journalEntryRepository repository;

    public void saveJournalEntry(journalEntry entry)
    {
        repository.save(entry);
    }

    public List<journalEntry> GetAll()
    {
        return repository.findAll();
    }

    public Optional<journalEntry> getEntryByItsID(String id)
    {
        return repository.findById(id);
    }

    public void updateTheEntryById(String id,journalEntry newEntry)
    {
        repository.save(newEntry);
    }

    public String DeleteTheEntryById(String id)
    {
        repository.deleteById(id);
        return "Deleted!!";
    }

}
