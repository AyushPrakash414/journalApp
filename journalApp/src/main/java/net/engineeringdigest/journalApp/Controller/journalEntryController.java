package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/journal")
public class journalEntryController {
    private Map<Long, journalEntry>listOfEntries=new HashMap<>();
    @GetMapping
    public List<journalEntry> getall()
    {
        return new ArrayList<journalEntry>(listOfEntries.values());
    }

    @PostMapping
    public boolean createEntry(@RequestBody journalEntry currentEntry)
    {
        listOfEntries.put(currentEntry.getId(),currentEntry);
        return true;
    }

    @GetMapping ("id/{myid}")
    public journalEntry getJournalEntry(@PathVariable long myid)
    {
        return listOfEntries.get(myid);
    }

    @DeleteMapping ("delete/{myid}")
    public boolean deleteTheJournalById(@PathVariable long myid)
    {
        listOfEntries.remove(myid);
        return true;
    }

    @PutMapping("update/{myid}")
    public boolean updateTheJournalByID(@PathVariable long myid,@RequestBody journalEntry updatedEntry)
    {
        listOfEntries.put(myid,updatedEntry);
        return true;
    }

}
