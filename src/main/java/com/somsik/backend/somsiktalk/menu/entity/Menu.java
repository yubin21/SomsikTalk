package com.somsik.backend.somsiktalk.menu.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Menu {

    private String date;
    private Map<Integer, String> menuItems;

    public Menu() {
        menuItems = new HashMap<>();
    }

    public Menu(String date, Map menuItems) {
        this.date = date;
        this.menuItems = menuItems;
    }
}