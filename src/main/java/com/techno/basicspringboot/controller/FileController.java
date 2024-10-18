package com.techno.basicspringboot.controller;


import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.entity.BaseFile;
import com.techno.basicspringboot.service.BaseFileService;
import com.techno.basicspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/files")
public class FileController {
    @Autowired
    BaseFileService baseFileService;

    @PostMapping
    public ResponseEntity<BaseResponseDto> saveFile() {
        BaseResponseDto response = null;
        try {
            response = baseFileService.saveFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(response, response.getStatus());
    }
}
