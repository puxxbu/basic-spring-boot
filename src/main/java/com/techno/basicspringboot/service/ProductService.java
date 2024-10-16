package com.techno.basicspringboot.service;

import com.techno.basicspringboot.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getOne(Long id);
    List<Product> getAll();
    String save(Product product);
    String update(Long id, Product product);
    String delete(Long id);
}
