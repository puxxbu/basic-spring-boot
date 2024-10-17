package com.techno.basicspringboot.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techno.basicspringboot.dto.AuditingDto;
import lombok.Data;

@Data
public class CategoryResponseDto extends AuditingDto {
    private Long id;
    private String categoryName;

    @JsonIgnore
    private String message;
}
