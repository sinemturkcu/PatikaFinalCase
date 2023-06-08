package com.sinemturkcu.weatherapplication.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Embeddable
public class BaseAdditionalFields {

    @Column(name = "id_user_created_by")
    private Long createdBy;

    @Column(name = "id_user_updated_by")
    private Long updatedBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
}
