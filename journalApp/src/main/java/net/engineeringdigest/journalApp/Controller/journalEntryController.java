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

}
