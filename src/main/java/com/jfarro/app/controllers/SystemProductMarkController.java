package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemProductMarkController {

    @GetMapping("/inventario/marcas")
    public String showSystemMarks(Model model) {
        model.addAttribute("marksActive", true);
        return "sistema/product-mark";
    }
}
