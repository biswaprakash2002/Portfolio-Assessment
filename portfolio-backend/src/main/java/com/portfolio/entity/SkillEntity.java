package com.portfolio.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.portfolio.dto.ProficiencyLevel;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skills")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_name", nullable = false, length = 100)
    private String skillName;

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency_level")
    private ProficiencyLevel proficiencyLevel;

    @Column(name = "proficiency_percentage")
    private Integer proficiencyPercentage;

    @Column(name = "experience_years", precision = 3, scale = 1)
    private BigDecimal experienceYears;

    // Old URL field (kept for reference)
    // @Column(name = "icon_url")
    // private String iconUrl;

    // New BLOB field to store icon
    @Lob
    @Column(name = "icon_blob", columnDefinition = "MEDIUMBLOB")
    private byte[] iconBlob;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Many skills belong to one category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private SkillCategoryEntity category;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}