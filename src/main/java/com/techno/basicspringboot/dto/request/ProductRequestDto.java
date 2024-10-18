package com.techno.basicspringboot.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank
    private String name;
    @Min(0)
    private double price;
    private int quantity;
    private Long categoryId;
}
