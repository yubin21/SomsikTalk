package com.somsik.backend.somsiktalk.board.service;

import com.somsik.backend.somsiktalk.board.document.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemService {
    List<Item> getAllItems();
    Item getItemById(Long id);
    void deleteItemById(Long id);
    Item saveItem(Item item);
}


