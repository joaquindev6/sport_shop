package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carrito")
public class TiendaCarritoController {

    @GetMapping({"", "/"})
    public String showCarrito(Client client) {
        return "tienda/shoppingcar";
    }
}
