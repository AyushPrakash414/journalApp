package net.AyushPrakash.journalApp.Controller;

import net.AyushPrakash.journalApp.Util.JwtUtil;
import net.AyushPrakash.journalApp.service.userDetailIMPL;
import net.AyushPrakash.journalApp.service.userEntryService;
import net.AyushPrakash.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/publicController")
public class publicController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private userDetailIMPL userDetailImpl;
    @Autowired
    private userEntryService entry;
    @Autowired
    private JwtUtil jwt;
    @PostMapping ("/sighup")
    public ResponseEntity<String> sighup(@RequestBody User currentEntry) {
        entry.saveNewUser(currentEntry);
        return ResponseEntity.ok("User entry created successfully.");
    }

    @PostMapping ("/login")
    public String login(@RequestBody User user) {
        try
        {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUserName(),user.getPassword()));
            UserDetails userDetail=userDetailImpl.loadUserByUsername(user.getUserName());
            String token=jwt.generateToken(userDetail.getUsername());
            return token;
        }
        catch(Exception e)
        {

        }
    }

}
