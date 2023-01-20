package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemIndexStartController {

    @GetMapping({"", "/"})
    public String showSystemUser(Model model) {
        model.addAttribute("indexActive", true);
        return "sistema/index-start";
    }
}
