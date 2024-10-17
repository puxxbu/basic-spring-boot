package com.techno.basicspringboot.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techno.basicspringboot.dto.AuditingDto;
import lombok.Data;

@Data
public class ProductResponseDto extends AuditingDto {
    private Long id;
    private String name;
    private double price;
    private int quantity;

    @JsonIgnore
    private String message;

}
