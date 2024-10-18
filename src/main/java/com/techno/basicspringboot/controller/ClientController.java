package com.techno.basicspringboot.controller;


import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.CategoryRequestDto;
import com.techno.basicspringboot.dto.response.CatFactResponseDto;
import com.techno.basicspringboot.dto.response.CategoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<CatFactResponseDto> getCat(){
        String url = "https://catfact.ninja/fact";
        CatFactResponseDto response = restTemplate.getForObject(url, CatFactResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/categories")
    public ResponseEntity<BaseResponseDto> getAllCategories(){
        String url = "http://localhost:8002/v1/categories";
        BaseResponseDto response = restTemplate.getForObject(url, BaseResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/categories")
    public ResponseEntity<BaseResponseDto> saveCategory( @RequestBody  CategoryRequestDto category){
        String url = "http://localhost:8002/v1/categories";
        BaseResponseDto response = restTemplate.postForObject(url, category, BaseResponseDto.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<BaseResponseDto> updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDto category){
        String url = "http://localhost:8002/v1/categories/"+id;
        restTemplate.put(url, category);
        return ResponseEntity.ok().build();
    }

}
