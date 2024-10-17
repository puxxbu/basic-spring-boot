package com.techno.basicspringboot.service;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.CategoryRequestDto;

public interface CategoryService {
    BaseResponseDto getOne(Long id);
    BaseResponseDto getAll();
    BaseResponseDto save(CategoryRequestDto categoryRequestDto);
    BaseResponseDto update(Long id, CategoryRequestDto categoryRequestDto);
    BaseResponseDto delete(Long id);
    BaseResponseDto softDelete(Long id);
}
