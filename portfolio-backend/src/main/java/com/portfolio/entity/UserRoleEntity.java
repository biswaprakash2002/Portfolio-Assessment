package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "user_role",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uq_user_role",
            columnNames = {"user_id", "role_id"}
        )
    }
)
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;

    @PrePersist
    protected void onCreate() {
        this.assignedAt = LocalDateTime.now();
    }

    // getters & setters
    public Long getId() { return id; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public RoleEntity getRole() { return role; }
    public void setRole(RoleEntity role) { this.role = role; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
}
