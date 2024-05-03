package fr.dawan.mongodbdriver.untyped;

import lombok.RequiredArgsConstructor;
import org.bson.Document;

public record MongoDto(Document filter, int page, int size) {
}

/*@RequiredArgsConstructor
public class MongoDtoClassEquivalent {
    private final Document filter;
    private final int page;
    private final int size;

    public Document filter() {
        return filter;
    }

    public int page() {
        return page;
    }

    public int size() {
        return size;
    }
}*/
