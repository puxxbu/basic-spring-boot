package com.techno.basicspringboot.repository;

import com.techno.basicspringboot.entity.BaseFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;

public interface BaseFileRepository extends JpaRepository<BaseFile, Long> {

}
