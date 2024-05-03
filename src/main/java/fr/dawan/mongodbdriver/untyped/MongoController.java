package fr.dawan.mongodbdriver.untyped;

import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static fr.dawan.mongodbdriver.conf.ConfigurationManager.getCollection;

@RestController
@RequestMapping("api/{collection}")
public class MongoController {

    @GetMapping("all")
    public List<Document> getAll(@PathVariable String collection) {
        return getCollection(collection).find().into(new ArrayList<>());
    }

    @PostMapping("find")
    public List<Document> find(@PathVariable String collection, @RequestBody Document filter, @RequestParam int page, @RequestParam int size) {
        return getCollection(collection)
                .find(filter)
                .skip(page * size)
                .limit(size)
                .into(new ArrayList<>());
    }
}
