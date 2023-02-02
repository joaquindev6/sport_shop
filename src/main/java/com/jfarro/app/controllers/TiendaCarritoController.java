package com.jfarro.app.controllers;

import com.jfarro.app.models.domains.Client;
import com.jfarro.app.models.domains.Product;
import com.jfarro.app.models.domains.Sale;
import com.jfarro.app.models.domains.SaleDetail;
import com.jfarro.app.services.ClientService;
import com.jfarro.app.services.InvoiceService;
import com.jfarro.app.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
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

    @Autowired
    private ClientService clientService;

    @Autowired
    private InvoiceService invoiceService;

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

            //Valida si se ha seleccionado el mismo producto ya guardado en el carrito
            if (!products.isEmpty()) {
                boolean exits = products.stream().anyMatch(p -> p.getId().equals(idProducto));
                if (exits) {
                    return "redirect:/carrito";
                }
            }

            Optional<Product> productOptional = productService.findByIdProduct(idProducto);
            if (productOptional.isPresent()) {
                products.add(productOptional.get());
                model.addAttribute("cantidad", 0);
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
            products.forEach(p -> {
                log.info("PRODUCTO: " + p.getName());
            });
        }
        return "redirect:/carrito";
    }

    @PostMapping("/save_sale")
    public String saveSale(
            @RequestParam(value = "importes", required = false) Long[] idProduct,
            @RequestParam(value = "total", required = false) Double total,
            @RequestParam(value = "cantidad[]", required = false) Integer[] amount,
            @RequestParam(value = "subTotal[]", required = false) Double[] subTotal,
            SessionStatus sessionStatus,
            RedirectAttributes flash
    ) {
        Optional<Client> clientOptional = clientService.findByIdClient(1L);

        if (amount != null && amount.length > 0) {
            for (Integer cant: amount) {
                if (cant <= 0) {
                    flash.addFlashAttribute("info", "Debe ingresar alemnos 1 cantidad.");
                    return "redirect:/carrito";
                }
            }
        }

        if (!products.isEmpty() && clientOptional.isPresent() && idProduct != null && idProduct.length > 0) {

            //Validando el stock
            for (int i = 0; i < idProduct.length; i++) {
                Product product = productService.findByIdProduct(idProduct[i]).orElse(null);
                if (product != null && product.getAmount() <= 0) {
                    flash.addFlashAttribute("info", "No hay stock del producto: " + product.getName());
                    return "redirect:/carrito";
                }
                if (product != null && product.getAmount() < amount[i]) {
                    flash.addFlashAttribute("info", "La cantidad ingresada supera el stock actual del producto: " + product.getName());
                    return "redirect:/carrito";
                }
            }

            Client clientData = clientOptional.get();

            Sale sale = new Sale();
            sale.setClient(clientData);
            sale.setDateTime(LocalDateTime.now());
            sale.setEmail(clientData.getEmail());
            sale.setTotal(total);
            sale.setState("CONFIRMADO");
            invoiceService.saveSale(sale);

            for (int i = 0; i < idProduct.length; i++) {
                Product product = productService.findByIdProduct(idProduct[i]).orElse(null);
                if (product != null) {
                    SaleDetail saleDetail = new SaleDetail();
                    saleDetail.setProduct(product);
                    saleDetail.setNameProduct(product.getName());
                    saleDetail.setPriceProduct(product.getPrice());
                    saleDetail.setAmount(amount[i]);
                    saleDetail.setSubTotal(subTotal[i]);
                    saleDetail.setSale(sale);

                    invoiceService.saveSaleDetail(saleDetail);

                    //Actualiza el stock del producto
                    int amountUpdate = product.getAmount() - amount[i];
                    product.setAmount(amountUpdate);
                    productService.saveProduct(product);
                }
            }
            sessionStatus.setComplete();
            products = new ArrayList<>();
        } else {
            flash.addFlashAttribute("error", "Debe seleccionar un producto a comprar.");
            sessionStatus.setComplete();
            return "redirect:/carrito";
        }
        return "redirect:/carrito/venta-completada";
    }

    @GetMapping("/venta-completada")
    public String showSalefinish(Client client) {
        return "tienda/salefinish";
    }

    @GetMapping(value = "/cargar-productos/{id}", produces = "application/json")
    public @ResponseBody Product cargarProducto(@PathVariable("id") Long id) {
        return productService.findByIdProduct(id).orElse(null);
    }
}
