package com.techno.basicspringboot.repository;

import com.techno.basicspringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("UPDATE Product p SET p.isDeleted = true WHERE p.id = ?1")
    void softDelete(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = false")
    List<Product> findAllProduct();

    Optional<Product> findByIdAndIsDeletedFalse(Long id);
}
