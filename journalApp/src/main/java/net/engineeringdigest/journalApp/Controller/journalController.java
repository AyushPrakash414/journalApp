package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping ("id/{myid}")
    public journalEntry getJournalEntry(@PathVariable long myid)
    {
        return null;
    }

    @DeleteMapping ("delete/{myid}")
    public boolean deleteTheJournalById(@PathVariable long myid)
    {

        return true;
    }

    @PutMapping("update/{myid}")
    public boolean updateTheJournalByID(@PathVariable long myid,@RequestBody journalEntry updatedEntry)
    {

        return true;
    }

}
