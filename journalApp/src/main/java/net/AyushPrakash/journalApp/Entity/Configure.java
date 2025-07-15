package net.AyushPrakash.journalApp.Entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
@Getter
@Setter
@Document(collection = "config")
public class Configure {
    @Id
    private ObjectId id;
    private String key;
    private String value;

}
