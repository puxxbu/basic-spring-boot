package com.techno.basicspringboot.service.impl;

import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.repository.ProductRepository;
import com.techno.basicspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> getOne(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public String save(Product product) {
        try {
            productRepository.save(product);
            return "Product saved successfully";
        }catch(Exception e){
            e.printStackTrace();
            return "Failed to save product";
        }

    }

    @Override
    public String update(Long id, Product product) {


        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

        BeanUtils.copyProperties(product, oldProduct, "id");
//        oldProduct.setName(product.getName());
//        oldProduct.setPrice(product.getPrice());
//        oldProduct.setQuantity(product.getQuantity());


        productRepository.save(oldProduct);

        return  "Product updated successfully";

    }

    @Override
    public String delete(Long id) {
        productRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        productRepository.deleteById(id);
        return "Product deleted successfully";
    }
}
