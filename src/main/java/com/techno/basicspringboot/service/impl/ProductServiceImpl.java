package com.techno.basicspringboot.service.impl;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.ProductRequestDto;
import com.techno.basicspringboot.dto.response.ProductResponseDto;
import com.techno.basicspringboot.entity.Category;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.exception.ProductNotFoundException;
import com.techno.basicspringboot.repository.CategoryRepository;
import com.techno.basicspringboot.repository.ProductRepository;
import com.techno.basicspringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Array;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public ProductResponseDto getOneProduct(Long id) {
       ProductResponseDto productResponseDto = new ProductResponseDto();
        Optional<Product> product = Optional.ofNullable(productRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new ProductNotFoundException("product with id " + id + "notfound")
        ));

        BeanUtils.copyProperties(product, productResponseDto);
        return productResponseDto;
    }

    @Override
    public BaseResponseDto getOne(Long id) {
       try {
           Map<String, Object> data = new HashMap<>();
           ProductResponseDto productResponseDto = getOneProduct(id);
           if(productResponseDto.getMessage() == null){
               data.put("product", productResponseDto);
               return BaseResponseDto.builder()
                       .status(HttpStatus.OK)
                       .description("Product found")
                       .data(data)
                       .build();
           }else{
               return BaseResponseDto.builder()
                       .status(HttpStatus.NOT_FOUND)
                       .description("Product not found")
                       .data(data)
                       .build();
           }

       } catch (Exception e) {
           return BaseResponseDto.builder()
                   .status(HttpStatus.NOT_FOUND)
                   .description("Product not found")
                   .data(new HashMap<>())
                   .build();
       }

    }

    @Override
    public BaseResponseDto getAll() {
       try {
           List<ProductResponseDto> responseDtos = new ArrayList<>();
           HashMap<String, Object> data = new HashMap<>();
           List<Product> products = productRepository.findAllProduct();



           if(!products.isEmpty()){
               for(Product product : products){
                   ProductResponseDto productResponseDto = new ProductResponseDto();
                   BeanUtils.copyProperties(product, productResponseDto);
                   responseDtos.add(productResponseDto);
               }
               data.put("products", responseDtos);
           }else{
               throw new ProductNotFoundException("No product found");
           }
           return BaseResponseDto.builder()
                   .status(HttpStatus.OK)
                   .description("Products found")
                   .data(data)
                   .build();
       } catch (ProductNotFoundException e) {
           return BaseResponseDto.builder()
                   .status(HttpStatus.NOT_FOUND)
                   .description("No product found")
                   .data(new HashMap<>())
                   .build();
       }
    }

    @Override
    public BaseResponseDto save(ProductRequestDto product) {
        try {
            Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(
                    () -> new ProductNotFoundException("category with id " + product.getCategoryId() + "notfound")
            );

            Product newProduct = new Product();

            newProduct.setName(product.getName());
            newProduct.setPrice(product.getPrice());
            newProduct.setQuantity(product.getQuantity());
            newProduct.setCategory(category);

            productRepository.save(newProduct);
            Map<String, Object> data = new HashMap<>();
            data.put("product", newProduct);
            return BaseResponseDto.builder()
                    .status(HttpStatus.CREATED)
                    .description("Product saved successfully")
                    .data(data)
                    .build();
        }catch(Exception e){
            return BaseResponseDto.builder().data(new HashMap<>())
                    .status(HttpStatus.BAD_REQUEST)
                    .description("Failed to save product")
                    .build();
        }

    }

    @Override
    public BaseResponseDto update(Long id, Product product) {


        try {
            ProductResponseDto productResponseDto = getOneProduct(id);
            BeanUtils.copyProperties(product, productResponseDto);
            Map<String, Object> data = new HashMap<>();
            data.put("product", product);
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Product updated successfully")
                    .data(data)
                    .build();
        } catch (ProductNotFoundException e) {
            return getOne(id);
        }

    }

    @Override
    public BaseResponseDto delete(Long id) {
        try {
            Map<String, Object> data = new HashMap<>();
            ProductResponseDto productResponseDto = getOneProduct(id);
            data.put("product" , productResponseDto);
            productRepository.deleteById(id);
            return  BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Product deleted successfully")
                    .data(data)
                    .build();
        } catch (ProductNotFoundException e) {
           return getOne(id);
        }
    }

    @Override
    @Transactional
    public BaseResponseDto softDelete(Long id) {
        try {
            Map<String, Object> data = new HashMap<>();
            ProductResponseDto productResponseDto = getOneProduct(id);
            data.put("product" , productResponseDto);
            productRepository.softDelete(id);
            return  BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Product deleted successfully")
                    .data(data)
                    .build();
        } catch (ProductNotFoundException e) {
            return getOne(id);
        }
    }

//    @Override
//    public String delete(Long id) {
//        try {
//            productRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
//            productRepository.deleteById(id);
//            return "Product deleted successfully";
//        } catch (HttpClientErrorException e) {
//            throw e;
//        } catch (Exception e) {
//
//            throw new RuntimeException("Gagal menghapus produk", e);
//        }
//    }
}
