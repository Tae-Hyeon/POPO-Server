package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Authority authority;

    private String phone;

    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public enum Authority {
        ROLE_USER, ROLE_ADMIN
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.authority = Authority.ROLE_USER;
    }

    public User(String email, String password, String phone) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.authority = Authority.ROLE_USER;
    }
}
