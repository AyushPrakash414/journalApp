package net.AyushPrakash.journalApp.Repository;

import net.AyushPrakash.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("check")
public class UserRepositoryIMPL {
    @Autowired
    private MongoTemplate mongoTemplate;
    @GetMapping
    public List<User> getUser()
    {
        Criteria criteria = new Criteria();
        Query query = new Query();
        query.addCriteria(
                criteria.andOperator(
                        Criteria.where("userName").exists(true)

                )
        );
        return mongoTemplate.find(query,User.class);
    }

}
