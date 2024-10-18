package com.techno.basicspringboot.controller;


import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.ProductRequestDto;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto> getOne(@PathVariable Long id) {
        BaseResponseDto response = productService.getOne(id);
        return new ResponseEntity<>(response, response.getStatus());

    }

    @PostMapping
    public ResponseEntity<BaseResponseDto> save(@Valid @RequestBody ProductRequestDto product) {

        BaseResponseDto response = productService.save(product);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAll(){
        BaseResponseDto response = productService.getAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> update(@PathVariable Long id, @RequestBody Product product){
       BaseResponseDto response = productService.update(id, product);
         return new ResponseEntity<>(response, response.getStatus());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> delete(@PathVariable Long id){
       BaseResponseDto response = productService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());

    }

    @DeleteMapping("/{id}/soft")
    public ResponseEntity<BaseResponseDto> softDelete(@PathVariable Long id){
        BaseResponseDto response = productService.softDelete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> delete(@PathVariable Long id){
//        String response = productService.delete(id);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
