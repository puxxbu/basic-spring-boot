package com.techno.basicspringboot.dto;


import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
public class AuditingDto implements Serializable {

    private Instant createdAt = Instant.now();
    private String createdBy;
    private Instant updatedAt = Instant.now();
    private String updatedBy;
}
