package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "days")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "popo_id", nullable = false)
    private Long popoId;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    @Column(name = "percent", nullable = false)
    private Float percent;

    @Column(name = "image", nullable = false)
    private String image;

    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    private java.sql.Timestamp createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    private java.sql.Timestamp updatedAt;
}
