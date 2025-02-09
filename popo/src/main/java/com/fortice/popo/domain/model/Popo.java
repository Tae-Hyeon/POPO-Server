package com.fortice.popo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fortice.popo.domain.popo.dto.PopoDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@SqlResultSetMapping(
        name = "PopoResponseMapping",
        classes = @ConstructorResult(
                targetClass = PopoDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "category", type = Integer.class),
                        @ColumnResult(name = "order", type = Integer.class),
                        @ColumnResult(name = "background", type = String.class)
                }
        )
)

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

    @JsonIgnore
    @Column(name = "concept_id", nullable = false)
    private int conceptId;

    @Column(name = "category", columnDefinition = "TINYINT", nullable = false)
    private int category;

    @Column(name = "`order`", columnDefinition = "TINYINT", nullable = false)
    private int order;

    @Column(name = "background")
    private String background;

    @Column(name = "tracker_image")
    private String tracker_image;

    @JsonIgnore
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @JsonIgnore
    public Integer getOwnerId(){
        return this.user.getId();
    }
}
