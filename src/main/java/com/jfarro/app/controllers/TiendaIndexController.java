package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TiendaIndexController {

    @GetMapping({"/", "/inicio"})
    public String showIndex() {
        return "tienda/index";
    }
}
