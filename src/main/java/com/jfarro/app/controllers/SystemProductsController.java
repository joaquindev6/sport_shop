package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Mark;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.models.domains.ProductSubCategory;
import com.jfarro.app.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/system-sport-shop")
@SessionAttributes("product")
public class SystemProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/inventario/productos")
    public String showSystemProducts(Product product, Model model, SessionStatus sessionStatus) {
        showDataProduct(model);
        sessionStatus.setComplete();
        return "sistema/products";
    }

    @PostMapping("/inventario/productos")
    public String saveProduct(@Valid Product product, BindingResult bindingResult, Model model, SessionStatus sessionStatus, RedirectAttributes flash) {
        if (bindingResult.hasErrors()) {
            showDataProduct(model);
            return "sistema/products";
        }
        productService.saveProduct(product);
        sessionStatus.setComplete();
        if (product.getId() != null && product.getId() > 0) {
            flash.addFlashAttribute("success", "Producto editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Producto registrado exitosamente.");
        }
        return "redirect:/system-sport-shop/inventario/productos";
    }

    @GetMapping("/inventario/producto-delete")
    public String upateSteteProduct(@RequestParam("id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            Optional<Product> productOptional = productService.findByIdProduct(id);
            if (productOptional.isPresent()) {
                byte state = 0;
                productService.updateStateProduct(state, id);
                flash.addFlashAttribute("success", "Producto eliminado exitosamente.");
            } else {
                flash.addFlashAttribute("error", "El producto a eliminar ya no existe en la base de datos.");
            }
        } else {
            flash.addFlashAttribute("error", "El ID del cliente no puede ser menor o igual a cero.");
        }
        return "redirect:/system-sport-shop/inventario/productos";
    }

    @GetMapping("/inventario/producto-edit")
    @ResponseBody
    public Product selectIdProduct(@RequestParam("id") Long id, Model model) {
        Product product = null;
        if (id > 0) {
            Optional<Product> productOptional = productService.findByIdProduct(id);
            if (productOptional.isPresent()) {
                product = productOptional.get();
                model.addAttribute("product", product);
            }
        }
        return product;
    }

    private void showDataProduct(Model model) {
        model.addAttribute("productsActive", true);

        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);

        List<Mark> marks = productService.findAllMarks();
        model.addAttribute("marks", marks);

        List<ProductSubCategory> subCategories = productService.findAllProductSubCategories();
        model.addAttribute("subCategories", subCategories);
    }
}
