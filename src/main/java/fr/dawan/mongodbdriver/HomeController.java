package fr.dawan.mongodbdriver;

import com.mongodb.client.MongoIterable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static fr.dawan.mongodbdriver.conf.ConfigurationManager.getDatabaseList;

@RestController
public class HomeController {

    @GetMapping("databases")
    public MongoIterable<String> getAllDatabaseNames() {
        return getDatabaseList();
    }
}
