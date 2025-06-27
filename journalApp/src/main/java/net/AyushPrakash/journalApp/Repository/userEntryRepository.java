package net.AyushPrakash.journalApp.Repository;

import net.AyushPrakash.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface userEntryRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUserName(String userName);
    void deleteByUserName(String Name);
}
