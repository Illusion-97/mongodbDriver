package fr.dawan.mongodbdriver.typed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    private ObjectId id; // ne pas mettre le "_"
    private String item;
    private int qty;
    private List<String> tags;
    private Size size;
}
