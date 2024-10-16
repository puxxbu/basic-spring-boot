package com.techno.basicspringboot.entity;

import com.techno.basicspringboot.dto.AuditingDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class Product extends AuditingDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double price;
    private int quantity;

}
