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

/* Esta clase controlador se encarga de conectar la logica de negocio con la vista. */

@Controller
@RequestMapping("/system-sport-shop")
@SessionAttributes("client")
public class SystemClientController {

    /* Atributos injectados con spring boot */

    @Autowired
    private ClientService clientService;

    @Autowired
    @Qualifier("client")
    private Validator validator;

    /* Este metodo initBinder carga las validaciones y modifica la informacion ingresada */
    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
        webDataBinder.registerCustomEditor(String.class, "names", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apePat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apeMat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "email", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "observation", new StringUppercaseEditor());
    }

    /* Handler encargado para mostrar datos de inicio al abrir la pagina */
    @GetMapping("/control/clientes")
    public String showSystemClients(Client client, Model model, SessionStatus sessionStatus) {
        showDataClient(model);
        sessionStatus.setComplete();
        return "sistema/clients";
    }

    /* Handler encargado en obtener los datos pasados por formulario, validarlos y guardarlos en la base de datos */
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
        sessionStatus.setComplete();
        return "redirect:/system-sport-shop/control/clientes";
    }

    /* Handler encargado de eliminar por id */
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

    /* Handler encargado de obtener datos por id y mandarlo como un JSON */
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

    /* Metodo para la configuracion y mostrar datos de inicio */
    private void showDataClient(Model model) {
        model.addAttribute("clientsActive", true);

        List<Client> clients = clientService.findALlClients();
        model.addAttribute("clients", clients);
    }
}
