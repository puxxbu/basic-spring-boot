package com.techno.basicspringboot.dto.request;

import lombok.Data;

@Data
public class ProductRequestDto {
    private String name;
    private double price;
    private int quantity;
}
