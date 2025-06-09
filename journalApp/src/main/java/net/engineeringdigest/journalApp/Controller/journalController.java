package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping ("/journal")
public class journalController {

   @Autowired
   private journalEntryService entry;
    @PostMapping
    public boolean createEntry(@RequestBody journalEntry currentEntry)
    {
        entry.saveJournalEntry(currentEntry);
        return true;
    }

    @GetMapping
    public List<journalEntry>GetAllJournalEntry()
    {
        return entry.GetAll();
    }

    @GetMapping ("id/{myid}")
    public Optional<journalEntry> getJournalEntryByid(@PathVariable String myid)
    {
        return entry.getEntryByItsID(myid);
    }

    @DeleteMapping ("delete/{myid}")
    public String deleteTheJournalById(@PathVariable String myid)
    {
        return entry.DeleteTheEntryById(myid);
    }

    @PutMapping("update/{myid}")
    public String updateTheJournalByID(@PathVariable String myid, @RequestBody journalEntry newEntry) {
        Optional<journalEntry> old_entry = getJournalEntryByid(myid);

        if (!old_entry.isPresent()) {
            return "Journal entry with the given ID not found!";
        }

        if (newEntry.getTitle().isBlank() || newEntry.getContent().isBlank()) {
            return "Either Title or the Content is Empty";
        }

        if (!old_entry.get().getId().equals(newEntry.getId())) {
            return "Id is not matching!!!";
        }

        entry.updateTheEntryById(myid, newEntry);
        return "Updated!!";
    }


}
