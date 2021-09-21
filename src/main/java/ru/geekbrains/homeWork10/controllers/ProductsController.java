package ru.geekbrains.homeWork10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.homeWork10.services.ProductsService;


@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/list")
    public String showProducts(Model uiModel) {
        uiModel.addAttribute("products", productsService.getProducts());
        return "products-th";
    }


    @GetMapping("/cost/min")
    public String showMinProducts(Model uiModel, @RequestParam("min") int min) {
        uiModel.addAttribute("products", productsService.getMinProducts(min));
        return "products-th";
    }

    @GetMapping("/cost/max")
    public String showMaxProducts(Model uiModel, @RequestParam("max") int max) {
        uiModel.addAttribute("products", productsService.getMaxProducts(max));
        return "products-th";
    }

    @GetMapping("/cost/between")
    public String showMinMaxProducts(Model uiModel, @RequestParam("min") int min, @RequestParam("max") int max) {
        uiModel.addAttribute("products", productsService.getMinMaxProducts(min, max));
        return "products-th";
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/page")
    public String showProduct(Model uiModel, @RequestParam("pageNum") int pageNum) {
        uiModel.addAttribute("products", productsService.getProductByPage(pageNum-1));
        return "products-th";
    }

}

