package com.somsik.backend.somsiktalk.board.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

//@AllArgsConstructor
@Setter
@Getter
@Document("item")
public class Item {

    @Id
    private Long somId;
    private String content;

    public Long getSomId() {
        return somId;
    }

    public void setSomId(Long somId) {
        this.somId = somId;
    }
}