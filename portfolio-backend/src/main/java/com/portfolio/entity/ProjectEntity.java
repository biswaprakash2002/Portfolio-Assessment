package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.portfolio.dto.ProjectStatus;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

//    @Column(name = "thumbnail_url")
//    private String thumbnailUrl;
    
    @Lob
    @Column(name = "thumbnail_blob", columnDefinition = "MEDIUMBLOB")
    private byte[] thumbnailBlob;

    @Column(name = "live_demo_url")
    private String liveDemoUrl;

    @Column(name = "source_code_url")
    private String sourceCodeUrl;

    @Column(name = "budget")
    private Double budget;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private ProjectStatus status;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}