package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.ProductCategory;
import com.jfarro.app.models.domains.ProductSubCategory;
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
@SessionAttributes("subcate")
public class SystemProductSubCategoryController {

    @Autowired
    private ProductService productService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, "name", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "description", new StringUppercaseEditor());
    }

    @GetMapping("/inventario/sub-categorias")
    public String showSystemaSubCategories(Model model, SessionStatus sessionStatus) {
        showDataSubCategory(model);
        sessionStatus.setComplete();
        return "sistema/product-sub-categories";
    }

    @PostMapping("/inventario/sub-categorias")
    public String saveSubCategory(@Valid @ModelAttribute("subcate") ProductSubCategory subcate,
                                  BindingResult bindingResult, Model model,
                                  SessionStatus sessionStatus, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            showDataSubCategory(model);
            model.addAttribute("errors", true);
            return "sistema/product-sub-categories";
        }
        if (subcate.getId() != null && subcate.getId() > 0) {
            flash.addFlashAttribute("success", "Sub categoría editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Sub categoría registrado exitosamente.");
        }
        productService.saveProductSubCategory(subcate);
        sessionStatus.setComplete();
        return "redirect:/system-sport-shop/inventario/sub-categorias";
    }

    @GetMapping("/inventario/sub-categoria-delete")
    public String updateState(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<ProductSubCategory> subCategoryOptional = productService.findByIdProductSubCategory(id);
            if (subCategoryOptional.isPresent()) {
                byte state = 0;
                productService.updateStateProductSubCategory(state, id);
                flash.addFlashAttribute("success", "Sub categoría eliminada exitosamente.");
            } else {
                flash.addFlashAttribute("error", "La sub categoría a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID de la sub categoría no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/inventario/sub-categorias";
    }

    @GetMapping("/inventario/sub-categoria-edit")
    @ResponseBody
    public ProductSubCategory selectIdSubCategory(@RequestParam("id") Long id, Model model) {
        ProductSubCategory subcate = null;
        if (id > 0) {
            Optional<ProductSubCategory> subCategoryOptional = productService.findByIdProductSubCategory(id);
            if (subCategoryOptional.isPresent()) {
                subcate = subCategoryOptional.get();
                model.addAttribute("subcate", subcate);
            }
        }
        return subcate;
    }

    @ModelAttribute("subcate")
    private ProductSubCategory getSubCategory() {
        return new ProductSubCategory();
    }

    private void showDataSubCategory(Model model) {
        model.addAttribute("subcatesActive", true);

        List<ProductSubCategory> subCategories = productService.findAllProductSubCategories();
        model.addAttribute("subcategories", subCategories);

        List<ProductCategory> categories = productService.findAllProductCategories();
        model.addAttribute("categories", categories);
    }
}
