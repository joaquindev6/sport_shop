package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deporte")
public class TiendaDeporteController {

    @GetMapping("/futbol/chimpunes")
    public String showFutbolChimpunes(Client client) {
        return "tienda/deporte-futbol-chimpunes";
    }
}
