package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.Repository.journalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class journalEntryService {

    @Autowired
    private journalEntryRepository repository;

    public void saveJournalEntry(journalEntry entry)
    {
        repository.save(entry);
    }

}
