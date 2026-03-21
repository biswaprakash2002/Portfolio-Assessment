package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Optional relation with User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private UserEntity user;

    // 🧾 Manual client details (used if NOT a user)
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @Column(name = "company_name", length = 150)
    private String companyName;

    @Column(name = "website_url", length = 255)
    private String websiteUrl;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // 📌 Future fields (important)
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}