package com.techno.basicspringboot.repository;

import com.techno.basicspringboot.entity.Category;
import com.techno.basicspringboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Modifying
    @Query("UPDATE Category c SET c.isDeleted = true WHERE c.id = ?1")
    void softDelete(@Param("id") Long id);

    @Query("SELECT c FROM Category c WHERE c.isDeleted = false")
    List<Category> findAllCategories();

    Optional<Category> findByIdAndIsDeletedFalse(Long id);

}
