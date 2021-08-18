package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @Column(name = "salt", nullable = false)
    private String salt;

    private String phone;

    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;
}
