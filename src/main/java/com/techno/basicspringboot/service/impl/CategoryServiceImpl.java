package com.techno.basicspringboot.service.impl;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.dto.request.CategoryRequestDto;
import com.techno.basicspringboot.dto.response.CategoryResponseDto;
import com.techno.basicspringboot.dto.response.ProductResponseDto;
import com.techno.basicspringboot.entity.Category;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.exception.ProductNotFoundException;
import com.techno.basicspringboot.repository.CategoryRepository;
import com.techno.basicspringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponseDto getOneCategory(Long id) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        Optional<Category> category = Optional.ofNullable(categoryRepository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new ProductNotFoundException("product with id " + id + "notfound")
        ));

        BeanUtils.copyProperties(category, categoryResponseDto);
        return categoryResponseDto;
    }

    @Override
    public BaseResponseDto getOne(Long id) {
        try {
            Map<String, Object> data = new HashMap<>();
            CategoryResponseDto categoryResponseDto = getOneCategory(id);
            if(categoryResponseDto.getMessage() == null){
                data.put("category", categoryResponseDto);
                return BaseResponseDto.builder()
                        .status(HttpStatus.OK)
                        .description("Category found")
                        .data(data)
                        .build();
            }else{
                return BaseResponseDto.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .description("Category not found")
                        .data(data)
                        .build();
            }

        } catch (Exception e) {
            return BaseResponseDto.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .description("Category not found")
                    .data(new HashMap<>())
                    .build();
        }

    }

    @Override
    public BaseResponseDto getAll() {
        try {
            List<CategoryResponseDto> responseDtos = new ArrayList<>();
            HashMap<String, Object> data = new HashMap<>();
            List<Category> categories = categoryRepository.findAllCategories();

            if(!categories.isEmpty()){
                for(Category category : categories){
                    CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
                    BeanUtils.copyProperties(category, categoryResponseDto);
                    responseDtos.add(categoryResponseDto);
                }
                data.put("categories", responseDtos);
            }else{
                throw new ProductNotFoundException("No category found");
            }
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("categories found")
                    .data(data)
                    .build();
        } catch (ProductNotFoundException e) {
            return BaseResponseDto.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .description("No category found")
                    .data(new HashMap<>())
                    .build();
        }
    }

    @Override
    public BaseResponseDto save(CategoryRequestDto categoryRequestDto) {
        try {
            Category category = new Category();
            BeanUtils.copyProperties(categoryRequestDto, category);
            categoryRepository.save(category);
            Map<String, Object> data = new HashMap<>();
            data.put("category", category);
            return BaseResponseDto.builder()
                    .status(HttpStatus.CREATED)
                    .description("Category saved successfully")
                    .data(data)
                    .build();
        }catch(Exception e){
            return BaseResponseDto.builder().data(new HashMap<>())
                    .status(HttpStatus.BAD_REQUEST)
                    .description("Failed to save category")
                    .build();
        }
    }

    @Override
    public BaseResponseDto update(Long id, CategoryRequestDto categoryRequestDto) {
        try {
            Category category = new Category();
            BeanUtils.copyProperties(categoryRequestDto, category);
            category.setId(id);
            categoryRepository.save(category);
            Map<String, Object> data = new HashMap<>();
            data.put("category", category);
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Category updated successfully")
                    .data(data)
                    .build();
        }catch(ProductNotFoundException e){
            return getOne(id);
        }
    }

    @Override
    public BaseResponseDto delete(Long id) {
        try {
            Map<String, Object> data = new HashMap<>();
            CategoryResponseDto categoryResponseDto = getOneCategory(id);
            categoryRepository.deleteById(id);
            data.put("category", categoryResponseDto);
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Category deleted successfully")
                    .data(data)
                    .build();
        }catch(ProductNotFoundException e){
            return getOne(id);
        }
    }

    @Override
    @Transactional
    public BaseResponseDto softDelete(Long id) {
        try {
            Map<String, Object> data = new HashMap<>();
            CategoryResponseDto categoryResponseDto = getOneCategory(id);
            data.put("category" , categoryResponseDto);
            categoryRepository.softDelete(id);
            return  BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("Category deleted successfully")
                    .data(data)
                    .build();
        } catch (ProductNotFoundException e) {
            return getOne(id);
        }
    }
}
