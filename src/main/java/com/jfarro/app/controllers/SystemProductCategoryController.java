package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemProductCategoryController {

    @GetMapping("/inventario/categorias")
    public String showSystemCategories(Model model) {
        model.addAttribute("catesActive", true);
        return "sistema/product-categories";
    }
}

