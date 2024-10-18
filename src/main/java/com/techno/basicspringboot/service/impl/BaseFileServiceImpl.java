package com.techno.basicspringboot.service.impl;

import com.techno.basicspringboot.dto.BaseResponseDto;
import com.techno.basicspringboot.entity.BaseFile;
import com.techno.basicspringboot.repository.BaseFileRepository;
import com.techno.basicspringboot.service.BaseFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BaseFileServiceImpl implements BaseFileService {

    @Autowired
    private final BaseFileRepository fileRepository;
    @Autowired
    private GenerateCsvServiceImpl generateCsvServiceImpl;

    @Override
    public BaseResponseDto saveFile() throws IOException {
        Map<String, Object> data = new HashMap<>();
        try {
            String base64Csv = generateCsvServiceImpl.generateCsvFromEntity();

            BaseFile fileEntity = new BaseFile();
            fileEntity.getFileName();
            fileEntity.setFileData(base64Csv);
            fileRepository.save(fileEntity);

            data.put("file", fileEntity);
            return BaseResponseDto.builder()
                    .status(HttpStatus.OK)
                    .description("File saved")
                    .data(data)
                    .build();



        } catch (Exception e) {
            return BaseResponseDto.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .description("Error while saving file")
                    .data(data)
                    .build();
        }
    }

    @Override
    public BaseResponseDto getFile(Long id) throws IOException {
        Map<String, Object> data = new HashMap<>();
        try {
            BaseFile fileEntity = fileRepository.findById(id).orElse(null);
            if (fileEntity != null) {
                data.put("file", fileEntity);
                return BaseResponseDto.builder()
                        .status(HttpStatus.OK)
                        .description("File found")
                        .data(data)
                        .build();
            } else {
                return BaseResponseDto.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .description("File not found")
                        .data(data)
                        .build();
            }

        } catch (Exception e) {
            return BaseResponseDto.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .description("Error while getting file")
                    .data(data)
                    .build();
        }
    }
}
