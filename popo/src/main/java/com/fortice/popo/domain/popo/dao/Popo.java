package com.fortice.popo.domain.popo.dao;

import lombok.*;
import javax.persistence.*;
import com.fortice.study.domain.user.dao.User;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "popos")
public class Popo {
    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    //@Column(name = "user_id", nullable = false)
    private User user;

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

    @Column(name = "created_at", nullable = false)
    private java.sql.Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private java.sql.Timestamp updatedAt;
}
