package net.AyushPrakash.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/Health")
    public String GetHealth()
    {
        return "Health is OK!!!";
    }
}
