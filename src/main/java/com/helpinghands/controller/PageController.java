package com.helpinghands.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/about")
    public String about() {
        return "about"; // Renders about.html
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // Renders contact.html
    }

    @GetMapping("/donate")
    public String donate() {
        return "donate"; // Renders donate.html
    }
}
