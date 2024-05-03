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
    public List<Document> find(@PathVariable String collection, @RequestBody MongoDto dto) {
        return getCollection(collection)
                .find(dto.filter())
                .skip(dto.page() * dto.size())
                .limit(dto.size())
                .into(new ArrayList<>());
    }
}
