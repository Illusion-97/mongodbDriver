package fr.dawan.mongodbdriver.untyped;

import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
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
                .projection(dto.projection())
                .sort(dto.sort())
                .skip(dto.page() * dto.size())
                .limit(dto.size())
                .into(new ArrayList<>());
    }

    @PostMapping("insert")
    public InsertOneResult insert(@PathVariable String collection, @RequestBody Document dto){
        return getCollection(collection).insertOne(dto);
    }

    @PutMapping("updateOne")
    public UpdateResult updateOne(@PathVariable String collection, @RequestBody MongoDto dto){
        return getCollection(collection).updateOne(dto.filter(), dto.subject());
    }
    @PutMapping("updateMany")
    public UpdateResult updateMany(@PathVariable String collection, @RequestBody MongoDto dto){
        return getCollection(collection).updateMany(dto.filter(), dto.subject());
    }

    @PutMapping("findAndReplace") // peu d'interêt
    public Document findAndReplace(@PathVariable String collection, @RequestBody MongoDto dto) {
        return getCollection(collection).findOneAndReplace(
                dto.filter(),
                dto.subject(),
                new FindOneAndReplaceOptions()
                        .upsert(true)
        );
    }
    @PutMapping("findAndUpdate")
    public Document findAndUpdate(@PathVariable String collection, @RequestBody MongoDto dto) {
        return getCollection(collection).findOneAndUpdate(
                dto.filter(),
                dto.subject(),
                new FindOneAndUpdateOptions()
                        .projection(dto.projection())
                        .sort(dto.sort())
                        .upsert(true)
        );
    }

    @DeleteMapping("findOneAndDelete")
    public Document findOneAndDelete(@PathVariable String collection, @RequestBody MongoDto dto) {
        return getCollection(collection).findOneAndDelete(dto.filter());
    }

    @DeleteMapping("deleteOne")
    public DeleteResult deleteOne(@PathVariable String collection, @RequestBody Document filter) {
        return getCollection(collection).deleteOne(filter);
    }
    @DeleteMapping("deleteMany")
    public DeleteResult deleteMany(@PathVariable String collection, @RequestBody Document filter) {
        return getCollection(collection).deleteMany(filter);
    }
}
