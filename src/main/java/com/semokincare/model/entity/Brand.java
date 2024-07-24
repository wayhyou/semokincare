package com.semokincare.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="m_brand")
@Schema(description="This table holds all brand detail information.")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier of the product.")
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String description;
    private String logoUrl;
    private Long createdAt;
    private Long updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date().getTime();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date().getTime();
    }
}
