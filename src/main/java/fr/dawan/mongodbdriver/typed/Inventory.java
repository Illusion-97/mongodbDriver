package fr.dawan.mongodbdriver.typed;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id; // ne pas mettre le "_"
    private String item;
    private int qty;
    private List<String> tags;
    private Size size;
}
