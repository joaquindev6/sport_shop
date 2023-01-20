package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemProductsController {

    @GetMapping("/inventario/productos")
    public String showSystemProducts(Model model) {
        model.addAttribute("productsActive", true);
        return "sistema/products";
    }
}
