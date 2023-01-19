package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deporte")
public class TiendaDeporteController {

    @GetMapping("/futbol/chimpunes")
    public String showFutbolChimpunes() {
        return "tienda/deporte-futbol-chimpunes";
    }
}
