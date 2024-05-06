package fr.dawan.mongodbdriver.typed;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import fr.dawan.mongodbdriver.conf.ConfigurationManager;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {
    MongoCollection<Inventory> collection = ConfigurationManager.getInstance().getDatabase("db")
            .withCodecRegistry(CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(), // Codec par défaut pour conserver le côté "non typé"
                    CodecRegistries.fromProviders( // Ajout d'un autre pour s'adapter à la classe
                            PojoCodecProvider.builder()
                                    .automatic(true)
                                    .build()
                    )
            ))
            .getCollection("inventory", Inventory.class); // récupération d'une collection typée


    @GetMapping
    public List<Inventory> findAll() {
        return collection.find().into(new ArrayList<>());
    }

    @PostMapping
    public InsertOneResult insertOne(@RequestBody Inventory inventory) {
        return collection.insertOne(inventory);
    }

    @PostMapping("many")
    public InsertManyResult insertMany(@RequestBody List<Inventory> inventories) {
        return collection.insertMany(inventories);
    }

    @GetMapping("findByItem/{item}")
    public List<Inventory> findByItem(@PathVariable String item) {
        /*Document filter = new Document();
        filter.append("item", item);
        return collection.find(filter).into(new ArrayList<>());*/

        /*Document filter = new Document("item", item);
        return collection.find(filter).into(new ArrayList<>());*/

        return collection.find(new Document("item", item)).into(new ArrayList<>());
    }

    @GetMapping("findByStatusAndQtyLt/{status}/{qty}")
    public List<Inventory> findByStatusAndQtyLessThan(@PathVariable String qty, @PathVariable String status) {

        return collection.find(Filters.and(
                Filters.eq("status",status),
                Filters.lt("qty", qty)
        )).into(new ArrayList<>());
    }


    @PutMapping("increaseQty/{increase}")
    public UpdateResult increaseQty(@PathVariable int increase) {
        // Pensez à verifier si une classe utilitaire n'existe pas déjà pour ce que vous souhaitez faire.
        return collection.updateMany(
                new Document(), // Quand une mise à jour, le filtre ne doit jamais être null, si vous souhaitez tout mettre à jour, il faut un document vide
                Updates.inc("qty", increase));
    }
}
