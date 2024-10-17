package com.techno.basicspringboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techno.basicspringboot.dto.AuditingDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category extends AuditingDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categoryName;


    private boolean isDeleted = false;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;


}
