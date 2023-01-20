package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemClientController {

    @GetMapping("/control/clientes")
    public String showSystemClients(Model model) {
        model.addAttribute("clientsActive", true);
        return "sistema/clients";
    }
}
