package ru.geekbrains.homeWork10.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homeWork10.entities.Product;

import java.util.List;

@Repository
public interface ProductsPageRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findAll();
}
