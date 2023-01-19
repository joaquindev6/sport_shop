package com.jfarro.app.controllers;

import com.jfarro.app.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaIndexController {

    @Autowired
    private ClientService clientService;

    @GetMapping({"/", "/inicio"})
    public String showIndex() {
        return "tienda/index";
    }
}
