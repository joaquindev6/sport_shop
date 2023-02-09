package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLogin(@RequestParam(name = "logout", required = false) String logout, Principal principal, RedirectAttributes flash, Client client) {

        if (logout != null) {
            flash.addFlashAttribute("info", "Sesión cerrada exitosamente.");
            return "redirect:/login";
        }

        return "login";
    }
}
