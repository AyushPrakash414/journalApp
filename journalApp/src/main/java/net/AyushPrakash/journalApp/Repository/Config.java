package net.AyushPrakash.journalApp.Repository;

import net.AyushPrakash.journalApp.Entity.Configure;
import net.AyushPrakash.journalApp.Entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface Config extends MongoRepository<Configure, ObjectId> {

}
