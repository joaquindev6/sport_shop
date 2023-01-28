package com.jfarro.app.controllers;

import com.jfarro.app.editors.StringUppercaseEditor;
import com.jfarro.app.models.domains.Mark;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.models.domains.ProductSubCategory;
import com.jfarro.app.services.FileDirectoryService;
import com.jfarro.app.services.ProductService;
import com.jfarro.app.utils.PathDirectoryEnums;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/system-sport-shop")
public class SystemProductsController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Autowired
    private FileDirectoryService fileDirectoryService;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, "name", new StringUppercaseEditor());
        webDataBinder.registerCustomEditor(String.class, "description", new StringUppercaseEditor());
    }

    @GetMapping("/inventario/productos")
    public String showSystemProducts(Product product, Model model, HttpServletRequest request) {
        showDataProduct(model);
        request.getSession().removeAttribute("product");
        return "sistema/products";
    }

    @PostMapping("/inventario/productos")
    public String saveProduct(@Valid Product product, BindingResult bindingResult,
                              Model model, @RequestParam("file") MultipartFile file,
                              RedirectAttributes flash, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            showDataProduct(model);
            model.addAttribute("errors", true);
            return "sistema/products";
        }

        if (product.getId() != null && product.getId() > 0) {
            //Obteniendo datos de la sesion al recuperar por id
            Product productSession = (Product) request.getSession().getAttribute("product");

            product.getUserHistory().setState(productSession.getUserHistory().getState());
            product.getUserHistory().setUserReg(productSession.getUserHistory().getUserReg());
            product.getUserHistory().setDateReg(productSession.getUserHistory().getDateReg());
            product.getUserHistory().setUserMod(productSession.getUserHistory().getUserMod());
            product.getUserHistory().setDateMod(productSession.getUserHistory().getDateMod());

            if (productSession.getPhoto() != null && productSession.getPhoto().length() > 0) {
                product.setPhoto(productSession.getPhoto());
            }
        }
        request.getSession().removeAttribute("product"); //Una ves recuperado los datos de la sesion se elimina el objeto de la sesion

        if (!file.isEmpty()) {

            if (product.getId() != null && product.getId() > 0
                    && product.getPhoto() != null && product.getPhoto().length() > 0) {
                fileDirectoryService.deleteFile(product.getPhoto(), PathDirectoryEnums.PRODUCT_FILE.directorys);
            }

            try {
                String filename = fileDirectoryService.copyFile(file, PathDirectoryEnums.PRODUCT_FILE.directorys);
                product.setPhoto(filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (product.getId() != null && product.getId() > 0) {
            flash.addFlashAttribute("success", "Producto editado exitosamente.");
        } else {
            flash.addFlashAttribute("success", "Producto registrado exitosamente.");
        }
        productService.saveProduct(product);
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

    @GetMapping({"/inventario/producto-edit", "/inventario/product-data"})
    @ResponseBody
    public Product selectIdProduct(HttpServletRequest request, @RequestParam("id") Long id) {
        Product product = null;
        if (id > 0) {
            Optional<Product> productOptional = productService.findByIdProduct(id);
            if (productOptional.isPresent()) {
                product = productOptional.get();
                request.getSession().setAttribute("product", product);
            }
        }
        return product;
    }

    @GetMapping("/photoproducts/{filename:.+}")
    public ResponseEntity<Resource> showPhotoImg(@PathVariable("filename") String filename) {
        Resource resource = null;
        try {
            resource = fileDirectoryService.loadFile(filename, PathDirectoryEnums.PRODUCT_FILE.directorys);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(resource.getFilename()).concat("\""))
                .body(resource);
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
