package com.jfarro.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemRolesController {

    @GetMapping("/control/roles")
    public String showSystemRoles(Model model) {
        model.addAttribute("rolesActive", true);
        return "sistema/roles";
    }
}
