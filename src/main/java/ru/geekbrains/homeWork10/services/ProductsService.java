package ru.geekbrains.homeWork10.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.homeWork10.entities.Product;
import ru.geekbrains.homeWork10.repositories.ProductsPageRepository;
import ru.geekbrains.homeWork10.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;
    private ProductsPageRepository productsPageRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Autowired
    public void setProductsPageRepository(ProductsPageRepository productsPageRepository) {
        this.productsPageRepository = productsPageRepository;
    }

    public List<Product> getProducts() {
        return productsRepository.findAll();
    }

    public List<Product> getMinProducts(int min) {
        return productsRepository.findAllByPriceLessThanOrderByPrice(min);
    }

    public List<Product> getMaxProducts(int max) {
        return productsRepository.findAllByPriceGreaterThanOrderByPrice(max);
    }

    public List<Product> getMinMaxProducts(int min, int max) {
        return productsRepository.findAllByPriceBetweenOrderByPrice(min, max);
    }

    public List<Product> getProductByPage(int pageNum) {
        Page<Product> pr = productsPageRepository.findAll(PageRequest.of(pageNum,5));
        return pr.stream().collect(Collectors.toList());
    }

    public Product getProductById(Long id) {
        Optional<Product> product = productsRepository.findById(id);
        return product.orElse(null);
    }

    public void deleteProductById(Long id) {
        productsRepository.deleteById(id);
    }

    public Product addNewProduct(Product product) {
        return productsRepository.save(product);
    }
}

