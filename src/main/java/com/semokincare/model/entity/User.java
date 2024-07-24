package com.semokincare.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="m_user")
@Schema(description="This table holds all user detail information.")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "Unique identifier of the user.")
    private String id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @Column(unique = true, nullable = false)
    private String email;
    private String fullName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String address;

    private String role;

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
}
