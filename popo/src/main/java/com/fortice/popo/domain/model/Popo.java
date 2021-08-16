package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "popos")
public class Popo {
    @Id
    @Column(name = "id")
    private Long id;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "concept_id", nullable = false)
    private Integer conceptId;

    @Column(name = "category", nullable = false)
    private Byte category;

    @Column(name = "type", nullable = false)
    private Byte type;

    @Column(name = "order", nullable = false)
    private Byte order;

    @Column(name = "background", nullable = false)
    private String background;

    @JsonIgnore
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public Popo(long id, long userId, int conceptId, Byte category, Byte type, Byte order, String background) {
        this.id = id;
        this.userId = userId;
        this.conceptId = conceptId;
        this.category = category;
        this.type = type;
        this.order = order;
        this.background = background;
    }
}
