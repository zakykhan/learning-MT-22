package com.graphDemo.mongoDBgraphQL.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tutorials")
public class Tutorial {

    @Id
    private String id;
    private String title;
    private String description;
    private String author_id;

}
