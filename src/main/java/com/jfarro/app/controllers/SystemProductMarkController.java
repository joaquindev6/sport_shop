package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.Mark;
import com.jfarro.app.services.ProductService;
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
@SessionAttributes("mark")
public class SystemProductMarkController {

    @Autowired
    private ProductService productService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, "name", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "description", new StringUppercaseEditor());
    }

    @GetMapping("/inventario/marcas")
    public String showSystemMarks(Mark mark, Model model, SessionStatus sessionStatus) {
        showDataMark(model);
        sessionStatus.setComplete();
        return "sistema/product-mark";
    }

    @PostMapping("/inventario/marcas")
    public String saveMark(@Valid Mark mark, BindingResult bindingResult, Model model, RedirectAttributes flash, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            showDataMark(model);
            model.addAttribute("errors", true);
            return "sistema/product-mark";
        }
        if (mark.getId() != null && mark.getId() > 0) {
            flash.addFlashAttribute("success", "Marca editada exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Marca registrada exitosamente.");
        }
        productService.saveMark(mark);
        sessionStatus.setComplete();
        return "redirect:/system-sport-shop/inventario/marcas";
    }

    @GetMapping("inventario/marca-delete")
    public String updateState(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<Mark> markOptional = productService.findByIdMarks(id);
            if (markOptional.isPresent()) {
                byte state = 0;
                productService.updateStateMark(state, id);
                flash.addFlashAttribute("success", "Marca eliminada exitosamente.");
            } else {
                flash.addFlashAttribute("error", "La marca a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID de la marca no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/inventario/marcas";
    }

    @GetMapping("/inventario/marca-edit")
    @ResponseBody
    public Mark selectIdMark(@RequestParam("id") Long id, Model model) {
        Mark mark = null;
        if (id > 0) {
            Optional<Mark> markOptional = productService.findByIdMarks(id);
            if (markOptional.isPresent()) {
                mark = markOptional.get();
                model.addAttribute("mark", mark);
            }
        }
        return mark;
    }

    private void showDataMark(Model model) {
        model.addAttribute("marksActive", true);

        List<Mark> marks = productService.findAllMarks();
        model.addAttribute("marks", marks);
    }
}
