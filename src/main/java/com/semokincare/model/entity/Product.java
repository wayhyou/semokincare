package com.semokincare.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="m_product")
@Schema(description="This table holds all product detail information.")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier of the product.")
    private String id;

    private String name;
    private String description;
    private Long price;
    private Integer stock;
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private CategoryProduct categoryProduct;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(nullable = false, updatable = false)
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

    public enum CategoryProduct {
        SKINCARE,
        HAIRCARE,
        MAKEUP,
        FRAGRANCE,
        BODY_CARE,
        NAIL_CARE,
        TOOLS,
        SUPPLEMENTS,
        MEN_CARE,
        SUN_CARE
    }
}
