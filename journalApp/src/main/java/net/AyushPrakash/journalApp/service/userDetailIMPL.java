package net.AyushPrakash.journalApp.service;

import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.Repository.userEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class userDetailIMPL implements UserDetailsService {
    @Autowired
    private userEntryRepository userRepo;
    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepo.findByUserName(username);
        if (user.isPresent())
        {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUserName())
                    .password(user.get().getPassword())
                    .roles(user.get().getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("Username Not Exist of name->"+username);
    }
}
