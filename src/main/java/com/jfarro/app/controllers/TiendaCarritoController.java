package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Client;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carrito")
@SessionAttributes("productList")
public class TiendaCarritoController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    private List<Product> products;

    public TiendaCarritoController() {
        products = new ArrayList<>();
    }

    @GetMapping({"", "/"})
    public String showCarrito(Client client) {
        return "tienda/shoppingcar";
    }

    @GetMapping("/additem")
    public String addItemCar(Client client, @RequestParam("id") Long idProducto, Model model) {
        if (idProducto > 0) {
            Optional<Product> productOptional = productService.findByIdProduct(idProducto);
            if (productOptional.isPresent()) {
                products.add(productOptional.get());
                model.addAttribute("productList", products);
            }
        }
        return "redirect:/carrito";
    }

    @GetMapping("/remove/{id}")
    public String removeItemCar(Client client, @PathVariable("id") Long idProducto, Model model) {
        if (idProducto > 0) {
            Optional<Product> productOptional = productService.findByIdProduct(idProducto);
            if (productOptional.isPresent()) {
                products.removeIf(product -> product.getId().equals(idProducto));
            }
        }
        return "redirect:/carrito";
    }

    @GetMapping(value = "/cargar-productos/{id}", produces = "application/json")
    public @ResponseBody Product cargarProducto(@PathVariable("id") Long id) {
        return productService.findByIdProduct(id).orElse(null);
    }
}
