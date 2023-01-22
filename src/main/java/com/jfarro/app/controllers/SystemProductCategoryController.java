package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.ProductCategory;
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
@SessionAttributes("category")
public class SystemProductCategoryController {

    @Autowired
    private ProductService productService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, "name", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "description", new StringUppercaseEditor());
    }

    @GetMapping("/inventario/categorias")
    public String showSystemCategories(Model model, SessionStatus sessionStatus) { //El model atribute  para que sea el nombre de la clase por defecto
        showDataCategory(model);
        sessionStatus.setComplete();
        return "sistema/product-categories";
    }

    @PostMapping("/inventario/categorias")
    public String saveCategory(@Valid @ModelAttribute("category") ProductCategory category,
                               BindingResult bindingResult, Model model,
                               SessionStatus sessionStatus, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            showDataCategory(model);
            model.addAttribute("errors", true);
            return "sistema/product-categories";
        }
        if (category.getId() != null && category.getId() > 0) {
            flash.addFlashAttribute("success", "Categoría editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Categoría registrado exitosamente.");
        }
        productService.saveProductCategory(category);
        sessionStatus.setComplete();
        return "redirect:/system-sport-shop/inventario/categorias";
    }

    @GetMapping("/inventario/categoria-delete")
    public String updateState(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<ProductCategory> categoryOptional = productService.findByIdProductCategory(id);
            if (categoryOptional.isPresent()) {
                byte state = 0;
                productService.updateStateProductCategory(state, id);
                flash.addFlashAttribute("success", "Categoría eliminada exitosamente.");
            } else {
                flash.addFlashAttribute("error", "La categoría a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID de la categoría no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/inventario/categorias";
    }

    @GetMapping("/inventario/categoria-edit")
    @ResponseBody
    public ProductCategory selectIdCategory(@RequestParam("id") Long id, Model model) {
        ProductCategory category = null;
        if (id > 0) {
            Optional<ProductCategory> categoryOptional = productService.findByIdProductCategory(id);
            if (categoryOptional.isPresent()) {
                category = categoryOptional.get();
                model.addAttribute("category", category);
            }
        }
        return category;
    }

    @ModelAttribute("category") //Como el nombre de clase es distinto a attributo pasado hay q instanciar para que asi no haya un null en la sesion
    public ProductCategory getCategory() {
        return new ProductCategory();
    }

    public void showDataCategory(Model model) {
        model.addAttribute("catesActive", true);

        //Hay que pasar la lista porque si no no podra iterar
        List<ProductCategory> categories = productService.findAllProductCategories();
        model.addAttribute("categories", categories);
    }
}

