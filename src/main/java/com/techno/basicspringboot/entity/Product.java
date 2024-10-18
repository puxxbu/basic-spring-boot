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
    private Long id;

    private String name;
    private Double price;
    private Integer quantity;
    @Column(nullable = true)
    private boolean isDeleted = false;


    @ManyToOne(targetEntity = Category.class, optional = false)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

}
