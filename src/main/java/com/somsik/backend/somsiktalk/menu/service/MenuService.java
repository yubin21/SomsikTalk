package com.somsik.backend.somsiktalk.menu.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.somsik.backend.somsiktalk.config.BaseException;
import com.somsik.backend.somsiktalk.menu.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final RestTemplate restTemplate;

    public List<Menu> getMenu() throws JsonProcessingException {
        String flaskUrl = "http://127.0.0.1:5000/crawling";
        String jsonResult = restTemplate.getForObject(flaskUrl, String.class);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<Map<String, List<String>>> typeReference = new TypeReference<Map<String, List<String>>>() {};

            Map<String, List<String>> jsonData = objectMapper.readValue(jsonResult, typeReference);

            List<Menu> menuList = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : jsonData.entrySet()) {
                Menu menu = new Menu();
                menu.setDate(entry.getKey());
                Map<Integer, String> menuItems = new HashMap<>();
                for (int i = 0; i < entry.getValue().size(); i++) {
                    menuItems.put(i + 1, entry.getValue().get(i));
                }
                menu.setMenuItems(menuItems);
                menuList.add(menu);
            }

            return menuList;
        } catch (Exception e) {
            // 예외 처리
            return Collections.emptyList(); // 또는 null 반환
        }
    }

}
