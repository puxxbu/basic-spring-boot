package com.techno.basicspringboot.service;

import com.techno.basicspringboot.dto.BaseResponseDto;

import java.io.IOException;

public interface BaseFileService {
    BaseResponseDto saveFile() throws IOException;
    BaseResponseDto getFile(Long id) throws IOException;

}
