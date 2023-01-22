package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.Client;
import com.jfarro.app.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/system-sport-shop")
@SessionAttributes("client")
public class SystemClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    @Qualifier("client")
    private Validator validator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
        webDataBinder.registerCustomEditor(String.class, "names", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apePat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apeMat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "email", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "observation", new StringUppercaseEditor());
    }

    @GetMapping("/control/clientes")
    public String showSystemClients(Client client, Model model, SessionStatus sessionStatus) {
        showDataClient(model);
        sessionStatus.setComplete();
        return "sistema/clients";
    }

    @PostMapping("/control/clientes")
    public String saveClient(@Valid Client client, BindingResult bindingResult, Model model, SessionStatus sessionStatus, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            showDataClient(model);
            model.addAttribute("errors", true);
            return "sistema/clients";
        }
        if (client.getId() != null && client.getId() > 0) {
            flash.addFlashAttribute("success", "Cliente editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Cliente registrado exitosamente.");
        }
        clientService.saveClient(client);
        sessionStatus.setComplete(); //Para q me guarde los datos completos del cliente y pueda ser modificado
        return "redirect:/system-sport-shop/control/clientes";
    }

    @GetMapping("/control/cliente-delete")
    public String updateState(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<Client> clientOptional = clientService.findByIdClient(id);
            if (clientOptional.isPresent()) {
                byte state = 0;
                clientService.updateStateClient(state, id);
                flash.addFlashAttribute("success", "Cliente eliminado exitosamente.");
            } else {
                flash.addFlashAttribute("error", "El cliente a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/control/clientes";
    }

    @GetMapping("/control/cliente-edit")
    @ResponseBody
    public Client selectIdClient(@RequestParam("id") Long id, Model model) {
        Client client = null;
        if (id > 0) {
            Optional<Client> clientOptional = clientService.findByIdClient(id);
            if (clientOptional.isPresent()) {
                client = clientOptional.get();
                model.addAttribute("client", client);
            }
        }
        return client;
    }

    private void showDataClient(Model model) {
        model.addAttribute("clientsActive", true);

        List<Client> clients = clientService.findALlClients();
        model.addAttribute("clients", clients);
    }
}
