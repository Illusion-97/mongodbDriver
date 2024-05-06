package fr.dawan.mongodbdriver.typed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    private double h;
    private double w;
    private String uom;
}
