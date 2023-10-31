package com.somsik.backend.somsiktalk.board;

import com.somsik.backend.somsiktalk.board.document.Item;
//import com.somsik.backend.somsiktalk.board.entity.Item;
import com.somsik.backend.somsiktalk.board.repository.ItemRepository;
import com.somsik.backend.somsiktalk.board.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board/items")
@RequiredArgsConstructor
public class BoardController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping
    public String items(Model model) {
//        List<Long> items = itemRepository.findAllIds();
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "board/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).orElse(null);
        model.addAttribute("item", item);
        return "board/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "board/addForm";
    }

    @PostMapping("/add")
    public String addItem(Item item, RedirectAttributes redirectAttributes) {
//        BoardService boardService = new boardService(item);
//        Item savedItem = itemRepository.save(item);
        Item savedItem = itemService.saveItem(item);
        redirectAttributes.addAttribute("itemId", savedItem.getSomId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId).orElse(null);
        model.addAttribute("item", item);
        return "board/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        Query query = new Query(Criteria.where("somId").is(itemId));
        Update update = new Update().set("content", item.getContent());
        mongoTemplate.updateFirst(query, update, Item.class);

//        itemRepository.updateItemContent(itemId, item.getContent());
//        itemRepository.update(itemId, item);
        return "redirect:/board/items/{itemId}";
    }
}
