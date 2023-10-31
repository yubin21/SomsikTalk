package com.somsik.backend.somsiktalk.board.service;

import com.somsik.backend.somsiktalk.board.document.Item;
import com.somsik.backend.somsiktalk.board.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private long sequence = 0L;
//    private long sequence;

//    @Autowired
//    public ItemServiceImpl(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//        String maxId = String.valueOf(itemRepository.findMaxSomId());
//        Long maxSomId = Long.parseLong(maxId);
//        sequence = (maxSomId != null) ? maxSomId : 0L;
//    }

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item saveItem(Item item) {
//        sequence = itemRepository.findMaxSomId();
        item.setSomId(++sequence);
        return itemRepository.save(item);
    }

}
