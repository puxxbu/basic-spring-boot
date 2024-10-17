package com.techno.basicspringboot.controller;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.CategoryRequestDto;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDto> getOne(@PathVariable Long id) {
        BaseResponseDto response = categoryService.getOne(id);
        return new ResponseEntity<>(response, response.getStatus());

    }

    @PostMapping
    public ResponseEntity<BaseResponseDto> save(@RequestBody CategoryRequestDto category){
        BaseResponseDto response = categoryService.save(category);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponseDto> getAll(){
        BaseResponseDto response = categoryService.getAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> update(@PathVariable Long id, @RequestBody CategoryRequestDto category){
        BaseResponseDto response = categoryService.update(id, category);
        return new ResponseEntity<>(response, response.getStatus());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> delete(@PathVariable Long id){
        BaseResponseDto response = categoryService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());

    }


    @DeleteMapping("/{id}/soft")
    public ResponseEntity<BaseResponseDto> softDelete(@PathVariable Long id){
        BaseResponseDto response = categoryService.softDelete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

}
