package com.techno.basicspringboot.entity;

import com.techno.basicspringboot.dto.AuditingDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category extends AuditingDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String categoryName;


    private boolean isDeleted = false;
}
