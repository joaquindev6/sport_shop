package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemUserController {

    @GetMapping("/control/usuarios")
    public String showSystemUsers(Model model) {
        model.addAttribute("usersActive", true); //Para activar la seleccion de las paginas en el menu
        return "sistema/users";
    }
}
