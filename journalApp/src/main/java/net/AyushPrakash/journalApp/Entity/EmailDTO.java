package net.AyushPrakash.journalApp.Entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class EmailDTO {
    private String from;
    private String to;
    private String subject;
    private String body;
}
