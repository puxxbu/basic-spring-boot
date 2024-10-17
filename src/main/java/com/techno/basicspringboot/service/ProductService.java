package com.techno.basicspringboot.service;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.response.ProductResponseDto;
import com.techno.basicspringboot.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    BaseResponseDto getOne(Long id);
    BaseResponseDto getAll();
    BaseResponseDto save(Product product);
    BaseResponseDto update(Long id, Product product);
    BaseResponseDto delete(Long id);
    BaseResponseDto softDelete(Long id);
}
