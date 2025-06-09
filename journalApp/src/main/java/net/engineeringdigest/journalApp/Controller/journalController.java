package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import net.engineeringdigest.journalApp.service.journalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalController {

    @Autowired
    private journalEntryService entry;

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody journalEntry currentEntry) {
        entry.saveJournalEntry(currentEntry);
        return ResponseEntity.ok("Journal entry created successfully.");
    }

    @GetMapping
    public ResponseEntity<List<journalEntry>> getAllJournalEntry() {
        List<journalEntry> allEntries = entry.GetAll();
        return ResponseEntity.ok(allEntries);
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<?> getJournalEntryByid(@PathVariable String myid) {
        Optional<journalEntry> result = entry.getEntryByItsID(myid);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("delete/{myid}")
    public ResponseEntity<String> deleteTheJournalById(@PathVariable String myid) {
        String response = entry.DeleteTheEntryById(myid);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update/{myid}")
    public ResponseEntity<String> updateTheJournalByID(@PathVariable String myid,
                                                       @RequestBody journalEntry newEntry) {
        try {
            ResponseEntity<?> old_entry = getJournalEntryByid(myid);

            if (!old_entry.getStatusCode().is2xxSuccessful() || old_entry.getBody() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Journal entry with the given ID not found!");
            }

            // Properly extract the journalEntry from Optional
            Optional<?> optionalBody = (Optional<?>) old_entry.getBody();
            if (optionalBody.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Journal entry with the given ID not found!");
            }

            journalEntry existingEntry = (journalEntry) optionalBody.get();

            if (newEntry.getTitle().isBlank() || newEntry.getContent().isBlank()) {
                return ResponseEntity.badRequest().body("Either Title or the Content is Empty");
            }

            if (!existingEntry.getId().equals(newEntry.getId())) {
                return ResponseEntity.badRequest().body("Id is not matching!!!");
            }

            entry.updateTheEntryById(myid, newEntry);
            return ResponseEntity.ok("Updated!!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal Server Error: " + e.getMessage());
        }
    }

}
