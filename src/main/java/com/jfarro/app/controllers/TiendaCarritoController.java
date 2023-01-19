package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrito")
public class TiendaCarritoController {

    @GetMapping({"", "/"})
    public String showCarrito() {
        return "tienda/shoppingcar";
    }
}
