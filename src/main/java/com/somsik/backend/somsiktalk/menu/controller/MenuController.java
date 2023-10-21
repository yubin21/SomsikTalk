package com.somsik.backend.somsiktalk.menu.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.somsik.backend.somsiktalk.menu.entity.Menu;
import com.somsik.backend.somsiktalk.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu")
    public String GetMenu(Model model) throws JsonProcessingException {
        List<Menu> menus = menuService.getMenu();
        model.addAttribute("menus", menus);
        return "menu";
    }

}
