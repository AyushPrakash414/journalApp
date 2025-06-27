package net.AyushPrakash.journalApp.Controller;

import net.AyushPrakash.journalApp.Entity.User;
import net.AyushPrakash.journalApp.Repository.userEntryRepository;
import net.AyushPrakash.journalApp.service.userEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/Admin")
public class AdminController
{
    @Autowired
    userEntryService repository;
    @GetMapping("/GetAll")
    public ResponseEntity<?>GetAll()
    {
        List<User>All=repository.getAll();
        if (All!=null&&!All.isEmpty())
        {
            return new ResponseEntity<>(All, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
