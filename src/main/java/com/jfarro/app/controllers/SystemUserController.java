package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.DocumentType;
import com.jfarro.app.models.domains.Role;
import com.jfarro.app.models.domains.User;
import com.jfarro.app.services.UserService;
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
@SessionAttributes("user")
public class SystemUserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("user")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(validator);
        webDataBinder.registerCustomEditor(String.class, "names", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apePat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "apeMat", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "email", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "observation", new StringUppercaseEditor());
    }

    @GetMapping("/control/usuarios")
    public String showSystemUsers(User user, Model model, SessionStatus sessionStatus) {
        dataShowUser(model);
        sessionStatus.setComplete();
        return "sistema/users";
    }

    @PostMapping("/control/usuarios")
    public String saveUser(@Valid User user, BindingResult bindingResult, SessionStatus sessionStatus, Model model, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            dataShowUser(model);
            model.addAttribute("errors", true);
            return "sistema/users";
        }
        if (user.getId() != null && user.getId() > 0) {
            flash.addFlashAttribute("success", "Usuario editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Usuario registrado exitosamente.");
        }
        userService.saveUser(user);
        sessionStatus.setComplete();
        return "redirect:/system-sport-shop/control/usuarios";
    }

    @GetMapping("/control/usuario-delete")
    public String updateState(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<User> userOptional = userService.findByIdUser(id);
            if (userOptional.isPresent()) {
                Byte state = 0;
                userService.updateStateUser(state, id);
                flash.addFlashAttribute("success", "Usuario eliminado exitosamente.");
            } else {
                flash.addFlashAttribute("error", "El usuario a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID de usuario no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/control/usuarios";
    }

    @GetMapping("/control/usuario-edit")
    @ResponseBody
    public User selectIdUser(@RequestParam("id") Long id, Model model) { //Cuando se edita hay que guardarlo en la sesion para matener el id del usuario seleccionado
        User user = null;
        if (id > 0) {
            Optional<User> userOptional = userService.findByIdUser(id);
            if (userOptional.isPresent()) {
                user = userOptional.get();
                model.addAttribute("user", user);
            } else {
                return null;
            }
        } else {
            return null;
        }
        return user;
    }

    private void dataShowUser(Model model) {
        model.addAttribute("usersActive", true); //Para activar la seleccion de las paginas en el menu

        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        List<Role> roles = userService.findAllRoles();
        model.addAttribute("roles", roles);

        List<DocumentType> documentTypes = userService.findAllDocumentTypes();
        model.addAttribute("documentTypes", documentTypes);
    }
}
