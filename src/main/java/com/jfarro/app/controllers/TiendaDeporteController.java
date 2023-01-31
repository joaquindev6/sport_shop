package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Client;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/deporte")
public class TiendaDeporteController {

    @Autowired
    private ProductService productService;

    @GetMapping("/futbol/chimpunes")
    public String showFutbolChimpunes(Client client, Model model, RedirectAttributes flash, HttpServletRequest request) {

        if (request.getSession().getAttribute("productList") != null) {
            List<Product> productList = (List<Product>) request.getSession().getAttribute("productList");
            model.addAttribute("productList", productList);
        }

        List<Product> products = productService.findAllBySubCategoryName("FUTBOL-CHIMPUNES");
        if (!products.isEmpty()) {
            model.addAttribute("products", products);
        } else {
            flash.addFlashAttribute("info", "Actualmente no hay productos registrados");
        }
        return "tienda/deporte-futbol-chimpunes";
    }
}
