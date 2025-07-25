package net.AyushPrakash.journalApp.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Indexed(unique = true)
    private String userName;
    private String password;
    private List<String>roles;

    public boolean isSentementAnalysis() {
        return sentementAnalysis;
    }

    public void setSentementAnalysis(boolean sentementAnalysis) {
        this.sentementAnalysis = sentementAnalysis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @DBRef
    private List<journalEntry> journalEntries = new ArrayList<>();
    private boolean sentementAnalysis;
    private String email;
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<journalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<journalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }
}







