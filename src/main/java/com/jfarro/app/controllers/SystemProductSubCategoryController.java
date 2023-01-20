package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemProductSubCategoryController {

    @GetMapping("/inventario/sub-categorias")
    public String showSystemaSubCategories(Model model) {
        model.addAttribute("subcatesActive", true);
        return "sistema/product-sub-categories";
    }
}
