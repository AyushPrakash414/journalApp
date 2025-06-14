package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.Entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface journalEntryRepository extends MongoRepository<journalEntry, ObjectId>
{

}
