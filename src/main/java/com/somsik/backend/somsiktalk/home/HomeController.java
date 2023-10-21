package com.somsik.backend.somsiktalk.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirectToIndex() {
        return "redirect:/index.html";
    }

    @GetMapping("/talk")
    public String redirectToTalk() {
        return "somsiktalk.html";
    }
}

