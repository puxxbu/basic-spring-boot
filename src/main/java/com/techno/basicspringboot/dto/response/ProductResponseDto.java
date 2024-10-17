package com.techno.basicspringboot.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techno.basicspringboot.dto.AuditingDto;
import com.techno.basicspringboot.entity.Category;
import lombok.Data;

@Data
public class ProductResponseDto extends AuditingDto {
    private Long id;
    private String name;
    private double price;
    private int quantity;
    private Category category;

    @JsonIgnore
    private String message;

}
