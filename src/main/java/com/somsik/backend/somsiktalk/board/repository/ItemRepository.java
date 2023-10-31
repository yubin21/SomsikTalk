package com.somsik.backend.somsiktalk.board.repository;

import com.somsik.backend.somsiktalk.board.document.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ItemRepository extends MongoRepository<Item, Long> {
    Item findByContent(String content);
    Item findBySomId(Long somId);

    @Query(value = "{}", sort = "{ 'somId' : -1 }", fields = "{ 'somId' : 1 }")
    Long findMaxSomId();
}

//@Repository
//public class ItemRepository {
//
//    private static final Map<Long, Item> store = new HashMap<>(); //static
//    private static long sequence = 0L; //static
//
//    public Item save(Item item) {
//        item.setId(++sequence);
//        store.put(item.getId(), item);
//        return item;
//    }
//
//    public Item findById(Long id) {
//        return store.get(id);
//    }
//
//    public List<Item> findAll() {
//        return new ArrayList<>(store.values());
//    }
//
//    public void update(Long itemId, Item updateParam) {
//        Item findItem = findById(itemId);
//        findItem.setContent(updateParam.getContent());
//    }
//
//    public void clearStore() {
//        store.clear();
//    }
//
//}
