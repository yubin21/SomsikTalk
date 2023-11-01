package com.somsik.backend.somsiktalk.board.service;

import com.somsik.backend.somsiktalk.board.document.Item;
import com.somsik.backend.somsiktalk.board.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.itemRepository = itemRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
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
        item.setSomId(sequenceGeneratorService.generateSequence(Item.SEQUENCE_NAME));
        return itemRepository.save(item);
    }

}
