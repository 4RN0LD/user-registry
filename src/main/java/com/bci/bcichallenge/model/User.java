package com.bci.bcichallenge.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private Boolean isActive = true;
    @Column
    private String token;
    @Column
    @CreationTimestamp
    private LocalDateTime lastLogin;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Phone> phones;
}
