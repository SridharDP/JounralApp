package com.dp.demospring.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Journal_Entries")
@Data
@NoArgsConstructor
public class Journal {
    @Id
    private ObjectId id;
    private LocalDate date;
    private String title;
    private String content;
}
