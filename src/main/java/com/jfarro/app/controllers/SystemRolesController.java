package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.Role;
import com.jfarro.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/system-sport-shop")
@SessionAttributes("role")
public class SystemRolesController {

    @Autowired
    private UserService userService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, "name", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "code", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "description", new StringUppercaseEditor());
    }

    @GetMapping("/control/roles")
    public String showSystemRoles(Role role, Model model, SessionStatus sessionStatus) {
        showDataRole(model);
        sessionStatus.setComplete();
        return "sistema/roles";
    }

    @PostMapping("/control/roles")
    public String saveRole(@Valid Role role, BindingResult bindingResult, Model model, SessionStatus sessionStatus, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            showDataRole(model);
            model.addAttribute("errors", true);
            return "sistema/roles";
        }
        if (role.getId() != null && role.getId() > 0) {
            flash.addFlashAttribute("success", "Rol de usuario editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Rol de usuario registrado exitosamente.");
        }
        userService.saveRole(role);
        sessionStatus.setComplete();
        return "redirect:/system-sport-shop/control/roles";
    }

    @GetMapping("/control/role-delete")
    public String updateStateRole(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<Role> roleOptional = userService.findByIdRole(id);
            if (roleOptional.isPresent()) {
                byte state = 0;
                userService.updateStateRole(state, id);
                flash.addFlashAttribute("success", "Rol de usuario eliminado exitosamente.");
            } else {
                flash.addFlashAttribute("error", "El rol a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID de rol no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/control/roles";
    }

    @GetMapping("/control/role-edit")
    @ResponseBody
    public Role selectIdRole(@RequestParam("id") Long id, Model model) {
        Role role = null;
        if (id > 0) {
            Optional<Role> roleOptional = userService.findByIdRole(id);
            if (roleOptional.isPresent()) {
                role = roleOptional.get();
                model.addAttribute("role", role);
            }
        }
        return role;
    }

    private void showDataRole(Model model) {
        model.addAttribute("rolesActive", true);

        List<Role> roles = userService.findAllRoles();
        model.addAttribute("roles", roles);
    }
}
