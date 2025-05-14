package com.reactive.programming.challenge.infrastructure.adapter.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("capabilities_technologies")
public class CapabilityTechnologyEntity {

    @Column("technology_id")
    private Long technologyId;

    @Column("capability_id")
    private Long capabilityId;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Column("updated_at")
    private LocalDateTime updatedAt;
}
