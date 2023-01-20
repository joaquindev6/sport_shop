package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.Client;
import com.jfarro.app.services.ClientService;
import com.jfarro.app.validators.ClientValidator;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("client") //Guarda el cliente en la sesion cuando se registra o se inicia sesion
public class TiendaIndexController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientValidator clientValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(clientValidator);
        webDataBinder.registerCustomEditor(String.class, "names", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apePat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apeMat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "email", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "observation", new StringUppercaseEditor());
    }

    @GetMapping({"/", "/inicio"})
    public String showIndex(Client client, Model model) {
        return "tienda/index";
    }

    @PostMapping("/save-client")
    public String saveClient(@Valid Client client, BindingResult bindingResult, @RequestParam(name = "passconfirm") String passwordConfirm) {

        //Confirma que las contraseñas ingresedas sean iguales
        if (!client.getPassword().equals(passwordConfirm)) {
            return "tienda/index";
        }

        //Valida los errores
        if (bindingResult.hasErrors()) {
            return "tienda/index";
        }

        clientService.save(client);
        return "redirect:/";
    }
}
