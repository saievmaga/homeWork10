package ru.geekbrains.homeWork10.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homeWork10.entities.Product;
import ru.geekbrains.homeWork10.services.ProductsService;


import java.util.List;

@RestController
@RequestMapping("/app")
public class ProductsRestController {
    private ProductsService productsService;

    @Autowired
    public void setProductService(ProductsService productsService) {

        this.productsService = productsService;
    }

   @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProductById(@PathVariable("id") Long id){
        return productsService.getProductById(id);
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getProducts(){
        return productsService.getProducts();
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Product newProduct(@RequestBody Product product) {
        return productsService.addNewProduct(product);
    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProductById(@PathVariable("id") Long id) {
        productsService.deleteProductById(id);
    }
}
