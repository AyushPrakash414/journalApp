package net.AyushPrakash.journalApp.Repository;

import net.AyushPrakash.journalApp.Entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface journalEntryRepository extends MongoRepository<journalEntry, ObjectId>
{

}
