package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fortice.popo.domain.tracker.dto.DayResponse;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "popos")
public class Popo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
//    @JoinColumn(name = "user_id", nullable = false)
//    private int userId;

    @Column(name = "concept_id", nullable = false)
    private int conceptId;

    @Column(name = "category", columnDefinition = "TINYINT", nullable = false)
    private int category;

    @Column(name = "`type`", columnDefinition = "TINYINT", nullable = false)
    private int type;

    @Column(name = "`order`", columnDefinition = "TINYINT", nullable = false)
    private int order;

    @Column(name = "background")
    private String background;

    @JsonIgnore
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    public void printProperties(){
        System.out.println(id);
        System.out.println(conceptId);
        System.out.println(category);
        System.out.println(type);
        System.out.println(order);
        System.out.println(background);
        System.out.println(createdAt);
        System.out.println(updatedAt);
    }
}
